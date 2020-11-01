package ua.nure.khandzhian.it.lab2.util;

import org.xml.sax.SAXException;
import ua.nure.khandzhian.it.lab2.Constant;

import javax.xml.XMLConstants;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class TransformXMLToHTML {

    public static final String INVALID_XML = "Sorry, but your XML-document doesn't valid!";

    public static void main(String[] args) throws TransformerException, FileNotFoundException {
        TransformXMLToHTML transform = new TransformXMLToHTML();
        transform.transformXMLToHTML(Constant.PATH_TO_XML, Constant.PATH_TO_XSD, Constant.PATH_TO_XSL, "phone.html");
    }

    public void transformXMLToHTML(String pathToXML, String pathToXSL, String pathToHTML)
            throws TransformerException, FileNotFoundException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer(new StreamSource(pathToXSL));
        transformer.transform(new StreamSource(pathToXML),
                new StreamResult(new FileOutputStream(pathToHTML)));
    }

    public void transformXMLToHTML(String pathToXML, String pathToXSD, String pathToXSL, String pathToHTML)
            throws TransformerException, FileNotFoundException {
        if (!isValid(pathToXML, pathToXSD)) {
            System.err.println(INVALID_XML);
            return;
        }
        transformXMLToHTML(pathToXML, pathToXSL, pathToHTML);
    }

    private boolean isValid(String pathToXML, String pathToXSD) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(new StreamSource(pathToXSD))
                    .newValidator()
                    .validate(new StreamSource(pathToXML));
        } catch (SAXException | IOException exception) {
            return false;
        }
        return true;
    }
}
