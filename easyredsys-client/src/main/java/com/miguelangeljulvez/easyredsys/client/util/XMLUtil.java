package com.miguelangeljulvez.easyredsys.client.util;

import com.miguelangeljulvez.easyredsys.client.core.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XMLUtil {

    public static String toRedsysXML(MessageOrderNoCESRequest message) {

        String msg = null;
        try {
            JAXBContext jaxbContext = null;
            if (message.getOrderNoCES() instanceof OrderNoCESRecurrence) {
                jaxbContext = JAXBContext.newInstance(MessageOrderNoCESRequest.class, OrderNoCESRecurrence.class);
            } else if (message.getOrderNoCES() instanceof OrderNoCESConfirmation) {
                jaxbContext = JAXBContext.newInstance(MessageOrderNoCESRequest.class, OrderNoCESConfirmation.class);
            } else {
                jaxbContext = JAXBContext.newInstance(MessageOrderNoCESRequest.class, OrderNoCES.class);
            }

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty("jaxb.fragment", Boolean.TRUE); //Quita la primera linea <?xml...>

            final StringWriter w = new StringWriter();
            jaxbMarshaller.marshal(message, w);
            msg = w.toString();
        } catch (JAXBException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return msg;
    }

    public static String toRedsysXML(OrderNoCES order) {

        String msg = null;
        try {
            JAXBContext jaxbContext = null;
            if (order instanceof OrderNoCESRecurrence) {
                jaxbContext = JAXBContext.newInstance(OrderNoCESRecurrence.class);
            } else if (order instanceof OrderNoCESConfirmation) {
                jaxbContext = JAXBContext.newInstance(OrderNoCESConfirmation.class);
            } else {
                jaxbContext = JAXBContext.newInstance(OrderNoCES.class);
            }

            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            //jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            jaxbMarshaller.setProperty("jaxb.fragment", Boolean.TRUE); //Quita la primera linea <?xml...>

            final StringWriter w = new StringWriter();
            jaxbMarshaller.marshal(order, w);
            msg = w.toString();
        } catch (JAXBException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return msg;
    }

    public static MessageOrderNoCESResponse fromRedsysXMLOrderNoCES(String xml) {

        JAXBContext jaxbContext = null;
        MessageOrderNoCESResponse messageOrderNoCESResponse = null;
        try {
            jaxbContext = JAXBContext.newInstance(MessageOrderNoCESResponse.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

            StringReader reader = new StringReader(xml);
            messageOrderNoCESResponse = (MessageOrderNoCESResponse) unmarshaller.unmarshal(reader);

        } catch (JAXBException e) {
            _log.log(Level.WARNING, e.getMessage(), e);
        }

        return messageOrderNoCESResponse;
    }

    private static final Logger _log = Logger.getLogger(XMLUtil.class.getName());
}
