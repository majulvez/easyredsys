# EASY REDSYS #

Esta librería facilita la integración con la pasarela de pago Redsys para aplicaciones Java.

Permite realizar compras CES, compras No CES, autorizaciones devoluciones, pagos diferidos y pagos recurrentes

**EN ESTOS MOMENTOS ESTÁ EN PROCESO DE PUBLICACIÓN EN LOS REPOSITORIOS MAVEN**

##Requisitos##

1. Java 1.8.x
3. Git (opcional)

## Instalación ##

En el submódulo "integration-example-war" hay un ejemplo de integración de esta librería.

Puedes ver el ejemplo de integración funcionando en esta dirección: http://easyredsys.miguelangeljulvez.com

###Integrar el jar en tu proyecto y modifica tu aplicación###

Añade como dependencia maven lo siguiente

```
<dependency>
  <group>com.miguelangeljulvez.easyredsys</group>
  <name>easyredsys-client</name>
  <version>1.0.0</version>
</dependency>
```

A continuación, implementa la interfaz AppConfig en una clase llamada por ejemplo AppConfigImpl e introduce los datos de tu pasarela de pago. Por ejemplo:

```
import com.miguelangeljulvez.easyredsys.client.AppConfig;

public class AppConfigImpl implements AppConfig {

    static String getMerchantCode() {
        return "061978060";
    }

    static String getTerminal() {
        return "001";
    }

    static String getSecretKey() {
        return "23423524234"; //Si testMode está establecido a true, no se usa. Se usa la clave de pruebas por defecto.
    }

    static boolean isTestMode() {
         return true; //Establécelo a false cuando quieras pasar a real
    }

    @Override
    public void saveNotification(Notification notification) {
        // Pon aquí lo que quieras hacer con la notificación recibida del banco. Ver apartado "Notificaciones" es este mismo fichero
    }
}
```

**OPCIÓN A - Compra mediante Comercio Electrónico Seguro (los datos de la tarjeta son solicitados por el banco)**

1- Crea la orden de compra en tu aplicación

El pedido puede crearse mediantes POJO

```
OrderCES orderCES = new OrderCES.Builder(AppConfigImpl.class)
                        .transactionType(TransactionType.AUTORIZACION)
                        .currency(Currency.EUR)
                        .consumerLanguage(Language.SPANISH)
                        .order("<Identificador único>")
                        .amount(<Cantidad a cobrar>)
                        .productDescription("Product description")
                        .paymentMethods(PaymentMethod.CARD)
                        .urlOk(<La url al terminar el proceso del banco con éxito>)
                        .urlKo(<La url al terminar el proceso del banco con fallo>)
                        .urlNotification(<La url de tu servicio de recogida de notificaciones>)
                        .build();

MessageOrderCESRequest messageOrderCESRequest = new MessageOrderCESRequest().Builder(AppConfigImpl.class)
                                    .withOrder(orderCES)
                                    .build();
```

2-Crear el formulario de envío para comunicarse con el banco

```
<form action="<%=messageOrderCESRequest.getRedsysUrl()%>" method="post">
    <input name="Ds_SignatureVersion" value="<%=messageOrderCESRequest.getDs_SignatureVersion()%>" type="hidden"/>
    <input name="Ds_MerchantParameters" value="<%=messageOrderCESRequest.getDs_MerchantParameters()%>" type="hidden"/>
    <input name="Ds_Signature" value="<%=messageOrderCESRequest.getDs_Signature()%>" type="hidden"/>
    <input type="submit" value="Submit"/>
</form>
```
    
3- Crea las páginas de urlOk y urlKo del banco

4- Indica qué hacer con las notificaciones del banco. Ver mas adelante el apartado 'Notificaciones'

**OPCIÓN B - Compra mediante Comercio Electrónico No Seguro (los datos de la tarjeta son solicitados por tu aplicación)**

1- Crea la orden de compra en tu aplicación
```
OrderNoCES orderNoCES = new OrderNoCES.Builder(AppConfigImpl.class)
            .transactionType(TransactionType.AUTORIZACION)
            .currency(Currency.EUR)
            .order(<Identificador único>)
            .amount(<Cantidad a cobrar>)
            .cardNumber("4548812049400004")
            .cvv2("123")
            .expiryDate("2012")
            .build();

MessageOrderNoCESRequest messageOrderNoCESRequest = new MessageOrderNoCESRequest.Builder(AppConfigImpl.class)
                .withOrder(orderNoCES)
                .build();

try {
    MessageOrderNoCESResponse messageOrderNoCESResponse = EasyRedsysService.request(messageOrderNoCESRequest, AppConfigImpl.class);
} catch (OperationException e) {
    e.printStackTrace();
}
```

2- Si no se produce ninguna excepción, la petición se ha realizado correctamente.

Si se produjera alguna excepción, la descripción del error aparecerá en los logs. También se puede obtener el código de error desde la excepción:

```
    e.getCode();
```

## Notificaciones ##

```
<dependency>
  <group>com.miguelangeljulvez.easyredsys</group>
  <name>easyredsys-server</name>
  <version>1.0.0</version>
</dependency>
```

Las notificaciones se reciben en el método saveNotificacion() de la interfaz AppConfig que has implementado previamente.

Las notificaciones que llegan a ese método han pasado todas las verificaciones y controles de seguridad.

Para publicar los diferentes servicios, deberás copiar el contenido de los ficheros web.xml, beans.xml y server-config.wsdd del submódulo "integration-example-war" al directorio WEB-INF de tu aplicación.

### Notificación ON-LINE: síncrona y asíncrona ###
```
orderCES.setDs_merchant_merchantURL("https://<servidor>/<context>/rest/InotificacionSIS");

o desde el builder del constructor

new OrderCES.Builder()
   ...
   .urlNotificacion("https://<servidor>/<context>/rest/InotificacionSIS")
   .build()
```
### Notificación ON-LINE: SOAP sin wsdl y con wsdl ###
```
orderCES.setDs_merchant_merchantURL("https://<servidor>/<context>/axis/InotificacionSIS");

o desde el builder del constructor

new OrderCES.Builder()
   ...
   .urlNotificacion("https://<servidor>/<context>/axis/InotificacionSIS")
   .build()
```
### Notificación ON-LINE; SOAP literal (recomendada) ###
```
orderCES.setDs_merchant_merchantURL("https://<servidor>/<context>/literal/InotificacionSIS");

o desde el builder del constructor

new OrderCES.Builder()
   ...
   .urlNotificacion("https://<servidor>/<context>/literal/InotificacionSIS")
   .build()
```