package ua.nure.khandzhian.it.lab2.parser;

import org.xml.sax.SAXException;
import ua.nure.khandzhian.it.lab2.Constant;
import ua.nure.khandzhian.it.lab2.entity.Phones;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;

public class JAXBParser {

    public Phones unmarshalling(String pathToXml, String pathToXsd) throws JAXBException, SAXException {
        JAXBContext jaxbContext = JAXBContext.newInstance(Phones.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(pathToXsd));
        unmarshaller.setSchema(schema);

        return (Phones) unmarshaller.unmarshal(new File(pathToXml));
    }

    public void marshalling(Phones phones) throws JAXBException {
        JAXBContext context = JAXBContext.newInstance(Phones.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, Constant.PHONE_XSD_NAMESPACE);
        marshaller.marshal(phones, new File(Constant.JAXB_XML));
    }

    public static void main(String[] args) throws JAXBException, SAXException {
        JAXBParser jaxbParser = new JAXBParser();
        Phones phones = jaxbParser.unmarshalling(Constant.PATH_TO_XML, Constant.PATH_TO_XSD);
        jaxbParser.marshalling(phones);
    }
}
