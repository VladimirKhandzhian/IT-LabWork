package ua.nure.khandzhian.it.lab2.parser;

import org.codehaus.stax2.XMLInputFactory2;
import org.codehaus.stax2.XMLStreamReader2;
import org.codehaus.stax2.validation.XMLValidationSchema;
import org.codehaus.stax2.validation.XMLValidationSchemaFactory;
import ua.nure.khandzhian.it.lab2.Constant;
import ua.nure.khandzhian.it.lab2.util.SaveToXML;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamSource;
import java.io.File;

import static javax.xml.stream.XMLInputFactory.newInstance;


public class STAX2Parser extends STAXParser{

    public void parse(String pathToXml, String pathToXsd) throws XMLStreamException {
        String currentElement = null;

        XMLInputFactory factory = newInstance();
        XMLInputFactory2 factory2 = (XMLInputFactory2) factory;
        factory2.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        factory2.setProperty(XMLInputFactory.IS_VALIDATING, Boolean.TRUE);

        XMLStreamReader2 reader2 = (XMLStreamReader2) factory2.createXMLStreamReader(new StreamSource(pathToXml));
        XMLValidationSchemaFactory schemaFactory = XMLValidationSchemaFactory.newInstance(XMLValidationSchema.SCHEMA_ID_W3C_SCHEMA);
        XMLValidationSchema schema = schemaFactory.createSchema(new File(pathToXsd));
        reader2.validateAgainst(schema);

        XMLEventReader reader = factory2.createXMLEventReader(reader2);

        while (reader.hasNext()) {
            XMLEvent event = reader.nextEvent();
            if (event.isCharacters() && event.asCharacters().isWhiteSpace()) {
                continue;
            }
            if (event.isStartElement()) {
                StartElement startElement = event.asStartElement();
                currentElement = startElement.getName().getLocalPart();
                startHandler(currentElement);
                attributeHandler(event);
                continue;
            }
            if (event.isCharacters()) {
                contentHandler(event, currentElement);
                continue;
            }
            if (event.isEndElement()) {
                endHandler(event);
                continue;
            }
        }
        reader.close();
    }

    public static void main(String[] args) throws XMLStreamException, TransformerException, ParserConfigurationException {
        STAX2Parser stax2Parser = new STAX2Parser();
        stax2Parser.parse(Constant.PATH_TO_XML, Constant.PATH_TO_XSD);
        SaveToXML.saveToXML(stax2Parser.getPhones(), Constant.STAX2_XML);
    }
}