package sis.redsys.api;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.Arrays;

import javax.crypto.*;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/*
CLASE DE IMPLEMENTACIÓN DE REDSYS
 */
public class ApiWsMacSha256 {

	/** Numero de bytes para obtener cadenas multiplos de 8 */
	private final short OCHO = 8;

	/** Constante de array de inicializaci�n */
	private final byte [] IV = {0, 0, 0, 0, 0, 0, 0, 0};

	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////// 					FUNCIONES AUXILIARES: 											  ///////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////

	public byte [] encrypt_3DES(final String claveHex, final String datos) throws IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException, InvalidKeyException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
		byte [] ciphertext = null;
		// Crea la clave
		DESedeKeySpec desKeySpec = new DESedeKeySpec(toByteArray(claveHex));
		SecretKey desKey = new SecretKeySpec(desKeySpec.getKey(), "DESede");
		// Crea un cifrador
		Cipher desCipher = Cipher.getInstance("DESede/CBC/NoPadding");

		// Inicializa el cifrador para encriptar
		desCipher.init(Cipher.ENCRYPT_MODE, desKey, new IvParameterSpec(IV));

		// Se a�aden los 0 en bytes necesarios para que sea un m�ltiplo de 8
		int numeroCerosNecesarios = OCHO - (datos.length() % OCHO);
		if (numeroCerosNecesarios == OCHO) {
			numeroCerosNecesarios = 0;
		}
		ByteArrayOutputStream array = new ByteArrayOutputStream();
		array.write(datos.getBytes("UTF-8"), 0, datos.length());
		for (int i = 0; i < numeroCerosNecesarios; i++) {
			array.write(0);
		}
		byte [] cleartext = array.toByteArray();
		// Encripta el texto
		ciphertext = desCipher.doFinal(cleartext);
		return ciphertext;
	}

	public String encodeB64String(final byte [] data) throws UnsupportedEncodingException {
		return new String(Base64.encodeBase64(data), "UTF-8");
	}

	public byte [] encodeB64(final byte [] data) {
		return Base64.encodeBase64(data);
	}

	public byte [] encodeB64UrlSafe(final byte [] data) {
		byte [] encode = Base64.encodeBase64(data);
		for (int i = 0; i < encode.length; i++) {
			if (encode[i] == '+') {
				encode[i] = '-';
			} else if (encode[i] == '/') {
				encode[i] = '_';
			}
		}
		return encode;
	}

	public String decodeB64String(final byte [] data) throws UnsupportedEncodingException {
		return new String(Base64.decodeBase64(data), "UTF-8");
	}

	public byte [] decodeB64(final byte [] data) {
		return Base64.decodeBase64(data);
	}

	public byte [] decodeB64UrlSafe(final byte [] data) {
		byte [] encode = Arrays.copyOf(data, data.length);
		for (int i = 0; i < encode.length; i++) {
			if (encode[i] == '-') {
				encode[i] = '+';
			} else if (encode[i] == '_') {
				encode[i] = '/';
			}
		}
		return Base64.decodeBase64(encode);
	}

	public String toHexadecimal(byte [] datos, int numBytes) {
		String resultado = "";
		ByteArrayInputStream input = new ByteArrayInputStream(datos, 0, numBytes);
		String cadAux;
		int leido = input.read();
		while (leido != -1) {
			cadAux = Integer.toHexString(leido);
			if (cadAux.length() < 2)// Hay que a�adir un 0
				resultado += "0";
			resultado += cadAux;
			leido = input.read();
		}
		return resultado;
	}

	public byte[] toByteArray(String cadena){
		//Si es impar se a�ade un 0 delante
		if(cadena.length() % 2 != 0)
			cadena = "0"+cadena;
			
		int longitud = cadena.length()/2;
		int posicion = 0;
		String cadenaAux =null;
		ByteArrayOutputStream salida = new ByteArrayOutputStream();
		for(int i=0 ;i < longitud ;i++)
		{
			cadenaAux = cadena.substring(posicion,posicion+2);
			posicion +=2;
			salida.write((char)Integer.parseInt(cadenaAux,16));
		}
		return salida.toByteArray();
	}

	public byte [] mac256(final String dsMerchantParameters, final byte [] secretKo) throws IllegalStateException, UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException {
		// Se hace el MAC con la clave de la operaci�n "Ko" y se codifica en BASE64
		Mac sha256HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(secretKo, "HmacSHA256");
		sha256HMAC.init(secretKey);
		byte [] hash = sha256HMAC.doFinal(dsMerchantParameters.getBytes("UTF-8"));
		return hash;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////// 		FUNCIONES PARA LA GENERACI�N DE LA PETICI�N DE PAGO: 				 ////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	public String getOrder(final String datos) {
		int posPedidoIni = datos.indexOf("<DS_MERCHANT_ORDER>");
		int tamPedidoIni = "<DS_MERCHANT_ORDER>".length();
		int posPedidoFin = datos.indexOf("</DS_MERCHANT_ORDER>");
		return datos.substring(posPedidoIni + tamPedidoIni, posPedidoFin);
	}

	public String createMerchantSignatureHostToHost(final String claveComercio, final String datosEntrada) throws UnsupportedEncodingException, InvalidKeyException, IllegalStateException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

		byte [] clave = decodeB64(claveComercio.getBytes("UTF-8"));
		String secretKc = toHexadecimal(clave, clave.length);
		byte [] secretKo = encrypt_3DES(secretKc, getOrder(datosEntrada));

		// Se hace el MAC con la clave de la operaci�n "Ko" y se codifica en BASE64
		byte [] hash = mac256(datosEntrada, secretKo);
		String res = encodeB64String(hash);
		return res;
	}
	

	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////// FUNCIONES PARA LA RECEPCI�N DE DATOS DE PAGO (Respuesta HOST to HOST): //////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////

	public String createSignatureResponseHostToHost(final String claveComercio, final String datosEntrada, final String numPedido) throws UnsupportedEncodingException, InvalidKeyException, IllegalStateException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, NoSuchPaddingException {

		byte [] clave = decodeB64(claveComercio.getBytes("UTF-8"));
		String secretKc = toHexadecimal(clave, clave.length);
		byte [] secretKo = encrypt_3DES(secretKc, numPedido);

		// Se hace el MAC con la clave de la operaci�n "Ko" y se codifica en BASE64
		byte [] hash = mac256(datosEntrada, secretKo);
		String res = encodeB64String(hash);
		return res;
	}
}