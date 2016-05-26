# EASY REDSYS #

Esta librería facilita la integración con la pasarela de pago Redsys para aplicaciones Java

##Requisitos##

1. Java 1.8.x o superior
3. Git

## Instalación ##

###Despliega el war para recibir las notificaciones###

1. Clona el repositorio: git clone git@bitbucket.org:majulvez/easy-redsys.git
2. Compila y verifica que los test se ejecutan correctamente: ./gradlew build
3. Copia la librería a tu proyecto easyredsys-client/build/libs/easyredsys-client-1.0.0.jar
4. Copia la librería a tu proyecto easyredsys-server/build/libs/easyredsys-server-1.0.0.jar (si quieres recibir usar easyredsys para recibir notificaciones)

###Integrar el jar en tu proyecto y modifica tu aplicación###

Añade como dependencia maven lo siguiente

```
#!java
<dependency>
  <group>com.miguelangeljulvez.easyredsys</group>
  <name>easyredsys-client</name>
  <version>1.0.0</version>
</dependency>
```

A continuación, implementa la interfaz AppConfig en una clase llamada por ejemplo AppConfigImpl e introduce los datos de tu pasarela de pago. Por ejemplo:

```
#!java
import com.miguelangeljulvez.easyredsys.client.AppConfig;

public class AppConfigImpl implements AppConfig {

    static String getMerchantCode() {
        return "061978060";
    }

    static String getTerminal() {
        return "001";
    }

    static String getSecretKey() {
        return "23423424234"; //Si testMode está establecido a true, no se usa. Se usa la clave de pruebas por defecto.
    }

    static boolean isTestMode() {
         return true; //Establécelo a false cuando quieras pasar a real
    }

    @Override
    public void saveNotification(Notification notification) {
        // Pon aquí lo que quieras hacer con la notificación recibida del banco. Ver punto "Notificaciones" es este mismo fichero
    }
}
```

**OPCIÓN A - Compra mediante Comercio Electrónico Seguro (los datos de la tarjeta son solicitados por el banco)**

1- Crea la orden de compra en tu aplicación

El pedido puede crearse mediantes POJO

```
#!java
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
#!java
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
#!java
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
#!java
    e.getCode();
```

## Notificaciones ##

```
#!java
<!--
<dependency>
  <group>com.miguelangeljulvez.easyredsys</group>
  <name>easyredsys-server</name>
  <version>1.0.0</version>
</dependency>
```

Para recibir las notificaciones del banco, tienes que crear una clase que implemente la interfaz com.miguelangeljulvez.easyredsys.UserAction

Esa clase será inyectada a los distintos componentes para poder validar las notificaciones del banco. Tienes un ejemplo en el módulo integration-example-war.

Tiene 2 métodos importantes:

1. saveNotification() -> recibe la notificación ya verificada y es donde deberás indicar qué es lo que quieres hacer con ella.
2. getSecretKey() -> La clave secreta del banco. Recuerda que la clave del entorno de pruebas y de producción es siempre distinta


### Notificación ON-LINE: síncrona y asíncrona ###
```
#!java
orderCES.setDs_merchant_merchantURL("https://<servidor>/<context>/rest/InotificacionSIS");

o desde el builder del constructor

new OrderCES.Builder()
   ...
   .urlNotificacion("https://<servidor>/<context>/rest/InotificacionSIS")
   .build()
```
### Notificación ON-LINE: SOAP sin wsdl y con wsdl ###
```
#!java
orderCES.setDs_merchant_merchantURL("https://<servidor>/<context>/axis/InotificacionSIS");

o desde el builder del constructor

new OrderCES.Builder()
   ...
   .urlNotificacion("https://<servidor>/<context>/axis/InotificacionSIS")
   .build()
```
### Notificación ON-LINE; SOAP literal (recomendada) ###
```
#!java
orderCES.setDs_merchant_merchantURL("https://<servidor>/<context>/literal/InotificacionSIS");

o desde el builder del constructor

new OrderCES.Builder()
   ...
   .urlNotificacion("https://<servidor>/<context>/literal/InotificacionSIS")
   .build()
```