package com.miguelangeljulvez.easyredsys.server.util;


import com.miguelangeljulvez.easyredsys.server.core.MessageOrderSOAPRequest;
import com.miguelangeljulvez.easyredsys.server.core.MessageOrderSOAPResponse;
import com.miguelangeljulvez.easyredsys.server.core.NotificationSOAP;
import com.miguelangeljulvez.easyredsys.server.core.OrderSOAP;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLSOAPUtil {

    public static synchronized String toRedsysXML(OrderSOAP orderSOAP) {

        String msg = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(OrderSOAP.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty("jaxb.fragment", Boolean.TRUE); //Quita la primera linea <?xml...>

            final StringWriter w = new StringWriter();
            jaxbMarshaller.marshal(orderSOAP, w);
            msg = w.toString();
        } catch (JAXBException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return msg;
    }

    public static synchronized String toRedsysXML(NotificationSOAP notificationSOAP) {

        String msg = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(NotificationSOAP.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty("jaxb.fragment", Boolean.TRUE); //Quita la primera linea <?xml...>

            final StringWriter w = new StringWriter();
            jaxbMarshaller.marshal(notificationSOAP, w);
            msg = w.toString();
        } catch (JAXBException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return msg;
    }

    public static synchronized String toRedsysXML(MessageOrderSOAPRequest messageOrderSOAPRequest) {

        String msg = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MessageOrderSOAPRequest.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty("jaxb.fragment", Boolean.TRUE); //Quita la primera linea <?xml...>

            final StringWriter w = new StringWriter();
            jaxbMarshaller.marshal(messageOrderSOAPRequest, w);
            msg = w.toString();
        } catch (JAXBException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return msg;
    }

    public static synchronized String toRedsysXML(MessageOrderSOAPResponse messageOrderSOAPResponse) {

        String msg = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MessageOrderSOAPResponse.class);

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty("jaxb.fragment", Boolean.TRUE); //Quita la primera linea <?xml...>

            final StringWriter w = new StringWriter();
            jaxbMarshaller.marshal(messageOrderSOAPResponse, w);
            msg = w.toString();
        } catch (JAXBException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return msg;
    }

    public static synchronized MessageOrderSOAPRequest fromRedsysXMLNotificationSOAP(String xml) {

        MessageOrderSOAPRequest messageOrderSOAPRequest = null;
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(MessageOrderSOAPRequest.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xml);
            messageOrderSOAPRequest = (MessageOrderSOAPRequest) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return messageOrderSOAPRequest;
    }

    private static final Logger _log = Logger.getLogger(XMLSOAPUtil.class.getName());

}
