package com.miguelangeljulvez.easyredsys.example.test;

import com.miguelangeljulvez.easyredsys.example.AppConfigImpl;
import com.miguelangeljulvez.easyredsys.server.ws.axis.InotificacionSISPortType;
import com.miguelangeljulvez.easyredsys.server.ws.axis.InotificacionSISService;
import com.miguelangeljulvez.easyredsys.server.ws.axis.InotificacionSISServiceLocator;
import com.miguelangeljulvez.easyredsys.server.ws.literal.InotificacionSIS;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.gradle.archive.importer.embedded.EmbeddedGradleImporter;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.namespace.QName;
import javax.xml.rpc.ServiceException;
import javax.xml.ws.Service;
import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

@RunWith(Arquillian.class)
public class ArquillianTest {

    @ArquillianResource
    URL deploymentUrl;

    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(EmbeddedGradleImporter.class)
                .forThisProjectDirectory()
                .importBuildOutput()
                .as(WebArchive.class)
                .addPackage("com.miguelangeljulvez.easyredsys")
                .addClass(AppConfigImpl.class)
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Test
    public void validRestNotificationTest()  {

        Client client = ClientBuilder.newClient();

        WebTarget target = client.target(deploymentUrl.toString() + "rest/InotificacionSIS");

        Form form = new Form();
        form.param("Ds_SignatureVersion", "HMAC_SHA256_V1");
        form.param("Ds_MerchantParameters", "eyJEc19EYXRlIjoiMjMlMkYwMyUyRjIwMTYiLCJEc19Ib3VyIjoiMTIlM0E0NCIsIkRzX1NlY3VyZVBheW1lbnQiOiIxIiwiRHNfQ2FyZF9Db3VudHJ5IjoiNzI0IiwiRHNfQW1vdW50IjoiMTAwMCIsIkRzX0N1cnJlbmN5IjoiOTc4IiwiRHNfT3JkZXIiOiJucnBkdHllcHpvdW4iLCJEc19NZXJjaGFudENvZGUiOiI1NTI3NDE2MSIsIkRzX1Rlcm1pbmFsIjoiMDAxIiwiRHNfUmVzcG9uc2UiOiIwMDAwIiwiRHNfTWVyY2hhbnREYXRhIjoiIiwiRHNfVHJhbnNhY3Rpb25UeXBlIjoiMCIsIkRzX0NvbnN1bWVyTGFuZ3VhZ2UiOiIxIiwiRHNfQXV0aG9yaXNhdGlvbkNvZGUiOiIxMzM3NzEifQ==");
        form.param("Ds_Signature", "Un6yOO1z-hVoNvrDPns4j9xVrI4l5nUcmH4zZfgeMAA=");

        Invocation.Builder request = target.request();

        Response response = request.post(Entity.entity(form, MediaType.APPLICATION_FORM_URLENCODED),Response.class);

        String entity = response.readEntity(String.class);

        Assert.assertEquals(200, response.getStatus());
    }


    @Test
    public void validAxisNotificationTest() throws RemoteException, MalformedURLException, ServiceException {
        final String wsURL = deploymentUrl.toString() + "axis/InotificacionSIS";
        URL url = new URL(wsURL + "?wsdl");

        final String requestXMLPath = "<Message><Request Ds_Version='0.0'><Fecha>24/03/2016</Fecha><Hora>09:33</Hora><Ds_SecurePayment>1</Ds_SecurePayment><Ds_Card_Country>724</Ds_Card_Country><Ds_Amount>1000</Ds_Amount><Ds_Currency>978</Ds_Currency><Ds_Order>tijvrzlhpspj</Ds_Order><Ds_MerchantCode>55274161</Ds_MerchantCode><Ds_Terminal>001</Ds_Terminal><Ds_Response>0000</Ds_Response><Ds_MerchantData></Ds_MerchantData><Ds_TransactionType>0</Ds_TransactionType><Ds_ConsumerLanguage>1</Ds_ConsumerLanguage><Ds_AuthorisationCode>135860</Ds_AuthorisationCode></Request><Signature>9wfgR15vvPWoDk3FWEmHZ2sJfcr1i2Z89oQrMZQvE9U=</Signature></Message>";
        final String expectedResponse = "<Message><Response Ds_version=\"0.0\"><Ds_Response_Merchant>OK</Ds_Response_Merchant></Response><Signature>Ml6olSSrH+QU9PXhFt6Sr8dHd78LUwoB8C81lfXElBw=</Signature></Message>";

        InotificacionSISService inotificacionSISService = new InotificacionSISServiceLocator();
        InotificacionSISPortType inotificacionSIS = inotificacionSISService.getInotificacionSIS(url);

        final String response = inotificacionSIS.procesaNotificacionSIS(requestXMLPath);

        Assert.assertEquals(expectedResponse, response);
    }

    @Test
    public void validLiteralNotificationTest() throws RemoteException, MalformedURLException, ServiceException {
        final String wsURL = deploymentUrl.toString() + "literal/InotificacionSIS";
        URL url = new URL(wsURL + "?wsdl");

        final String requestXMLPath = "<Message><Request Ds_Version='0.0'><Fecha>24/03/2016</Fecha><Hora>09:33</Hora><Ds_SecurePayment>1</Ds_SecurePayment><Ds_Card_Country>724</Ds_Card_Country><Ds_Amount>1000</Ds_Amount><Ds_Currency>978</Ds_Currency><Ds_Order>tijvrzlhpspj</Ds_Order><Ds_MerchantCode>55274161</Ds_MerchantCode><Ds_Terminal>001</Ds_Terminal><Ds_Response>0000</Ds_Response><Ds_MerchantData></Ds_MerchantData><Ds_TransactionType>0</Ds_TransactionType><Ds_ConsumerLanguage>1</Ds_ConsumerLanguage><Ds_AuthorisationCode>135860</Ds_AuthorisationCode></Request><Signature>9wfgR15vvPWoDk3FWEmHZ2sJfcr1i2Z89oQrMZQvE9U=</Signature></Message>";
        final String expectedResponse = "<Message><Response Ds_version=\"0.0\"><Ds_Response_Merchant>OK</Ds_Response_Merchant></Response><Signature>Ml6olSSrH+QU9PXhFt6Sr8dHd78LUwoB8C81lfXElBw=</Signature></Message>";

        QName qname = new QName("http://literal.ws.server.easyredsys.miguelangeljulvez.com/", "InotificacionSIS");
        Service service = Service.create(url, qname);

        InotificacionSIS inotificacionSIS = service.getPort(InotificacionSIS.class);

        final String response = inotificacionSIS.notificacion(requestXMLPath);

        Assert.assertEquals(expectedResponse, response);
    }
}
