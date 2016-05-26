package sis.redsys.api;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.Arrays;
import org.json.JSONObject;

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
public class ApiMacSha256 {

	/** Numero de bytes para obtener cadenas multiplos de 8 */
	private final short OCHO = 8;

	/** Constante de array de inicializaci�n */
	private final byte [] IV = {0, 0, 0, 0, 0, 0, 0, 0};

	/** Array de DatosEntrada */
	private JSONObject jsonObj = new JSONObject();

	/** Set parameter */
	public void setParameter(final String key, final String value) {
		jsonObj.put(key, value);
	}

	/** Get parameter */
	public String getParameter(final String key) {
		return jsonObj.getString(key);
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////// 					FUNCIONES AUXILIARES: 											  ///////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////

	/** 3DES Function 
	 * @throws InvalidKeyException 
	 * @throws NoSuchPaddingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws UnsupportedEncodingException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException */
	public byte [] encrypt_3DES(final String claveHex, final String datos) throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidAlgorithmParameterException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
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

	/** Base64 y HEX Functions 
	 * @throws UnsupportedEncodingException */
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

	/** MAC Function 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalStateException */
	public byte [] mac256(final String dsMerchantParameters, final byte [] secretKo) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException {
		// Se hace el MAC con la clave de la operaci�n "Ko" y se codifica en BASE64
		Mac sha256HMAC = Mac.getInstance("HmacSHA256");
		SecretKeySpec secretKey = new SecretKeySpec(secretKo, "HmacSHA256");
		sha256HMAC.init(secretKey);
		byte [] hash = sha256HMAC.doFinal(dsMerchantParameters.getBytes("UTF-8"));
		return hash;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////// 		FUNCIONES PARA LA GENERACI�N DEL FORMULARIO DE PAGO: 				 ////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	public String getOrder() {
		if (getParameter("DS_MERCHANT_ORDER") == null || getParameter("DS_MERCHANT_ORDER").equals("")) {
			return getParameter("Ds_Merchant_Order");
		} else {
			return getParameter("DS_MERCHANT_ORDER");
		}
	}

	public String createMerchantParameters() throws UnsupportedEncodingException {
		String jsonString = jsonObj.toString();
		String res = encodeB64String(jsonString.getBytes("UTF-8"));
		return res;
	}

	public String createMerchantSignature(final String claveComercio) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		String merchantParams = createMerchantParameters();

		byte [] clave = decodeB64(claveComercio.getBytes("UTF-8"));
		String secretKc = toHexadecimal(clave, clave.length);
		byte [] secretKo = encrypt_3DES(secretKc, getOrder());

		// Se hace el MAC con la clave de la operaci�n "Ko" y se codifica en BASE64
		byte [] hash = mac256(merchantParams, secretKo);
		String res = encodeB64String(hash);
		return res;
	}

	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////// FUNCIONES PARA LA RECEPCI�N DE DATOS DE PAGO (Notif, URLOK y URLKO): ////////////
	//////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////

	public String getOrderNotif() {
		if (getParameter("Ds_Order") == null || getParameter("Ds_Order").equals("")) {
			return getParameter("DS_ORDER");
		} else {
			return getParameter("Ds_Order");
		}
	}

	public String getOrderNotifSOAP(final String datos) {
		int posPedidoIni = datos.indexOf("<Ds_Order>");
		int tamPedidoIni = "<Ds_Order>".length();
		int posPedidoFin = datos.indexOf("</Ds_Order>");
		return datos.substring(posPedidoIni + tamPedidoIni, posPedidoFin);
	}

	public String getRequestNotifSOAP(final String datos) {
		int posReqIni = datos.indexOf("<Request");
		int posReqFin = datos.indexOf("</Request>");
		int tamReqFin = "</Request>".length();
		return datos.substring(posReqIni, posReqFin + tamReqFin);
	}

	public String getResponseNotifSOAP(final String datos) {
		int posResIni = datos.indexOf("<Response");
		int posResFin = datos.indexOf("</Response>");
		int tamResFin = "</Response>".length();
		return datos.substring(posResIni, posResFin + tamResFin);
	}

	public String decodeMerchantParameters(final String datos) throws UnsupportedEncodingException {
		byte [] res = decodeB64UrlSafe(datos.getBytes("UTF-8"));
		String params = new String(res, "UTF-8");
		jsonObj = new JSONObject(params);
		return new String(res, "UTF-8");
	}

	public String createMerchantSignatureNotif(final String claveComercio, final String merchantParams) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte [] clave = decodeB64(claveComercio.getBytes("UTF-8"));
		String secretKc = toHexadecimal(clave, clave.length);
		byte [] secretKo = encrypt_3DES(secretKc, getOrderNotif());

		// Se hace el MAC con la clave de la operaci�n "Ko" y se codifica en BASE64
		byte [] hash = mac256(merchantParams, secretKo);
		byte [] res = encodeB64UrlSafe(hash);
		return new String(res, "UTF-8");
	}

	/******  Notificaciones SOAP ENTRADA *****
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalStateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException */
	public String createMerchantSignatureNotifSOAPRequest(final String claveComercio, final String request) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte [] clave = decodeB64(claveComercio.getBytes("UTF-8"));
		String secretKc = toHexadecimal(clave, clave.length);
		byte [] secretKo = encrypt_3DES(secretKc, getOrderNotifSOAP(request));
		
		// Se hace el MAC con la clave de la operaci�n "Ko" y se codifica en BASE64
		byte [] hash = mac256(getRequestNotifSOAP(request), secretKo);
		byte [] res = encodeB64(hash);
		return new String(res, "UTF-8");
	}

	/******  Notificaciones SOAP SALIDA *****
	 * @throws UnsupportedEncodingException 
	 * @throws IllegalStateException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws BadPaddingException 
	 * @throws IllegalBlockSizeException 
	 * @throws InvalidAlgorithmParameterException 
	 * @throws NoSuchPaddingException */
	public String createMerchantSignatureNotifSOAPResponse(final String claveComercio, final String response, final String numPedido) throws UnsupportedEncodingException, InvalidKeyException, NoSuchAlgorithmException, IllegalStateException, NoSuchPaddingException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
		byte [] clave = decodeB64(claveComercio.getBytes("UTF-8"));
		String secretKc = toHexadecimal(clave, clave.length);
		byte [] secretKo = encrypt_3DES(secretKc, numPedido);
		
		// Se hace el MAC con la clave de la operaci�n "Ko" y se codifica en BASE64
		byte [] hash = mac256(getResponseNotifSOAP(response), secretKo);
		byte [] res = encodeB64(hash);
		return new String(res, "UTF-8");
	}	
}