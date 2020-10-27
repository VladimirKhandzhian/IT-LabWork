package ua.nure.khandzhian.it.lab2.parser;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;
import ua.nure.khandzhian.it.lab2.Constant;
import ua.nure.khandzhian.it.lab2.entity.Phone;
import ua.nure.khandzhian.it.lab2.entity.Phones;
import ua.nure.khandzhian.it.lab2.entity.enumeration.Capacity;
import ua.nure.khandzhian.it.lab2.entity.enumeration.Currency;
import ua.nure.khandzhian.it.lab2.entity.enumeration.XML;
import ua.nure.khandzhian.it.lab2.util.InitVariable;
import ua.nure.khandzhian.it.lab2.util.SaveToXML;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class SAXParser extends DefaultHandler {

    private String currentElement;
    private Phones phones;
    private Phone phone;

    @Override
    public void error(SAXParseException exception) throws SAXException {
        throw new SAXException("Invalid xml --> " + exception.getMessage());
    }

    public Phones getPhones() {
        return phones;
    }

    public void parse(String pathToXml, String pathToXsd) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setNamespaceAware(true);
        factory.setFeature(Constant.FEATURE_TURN_VALIDATION_ON, true);
        factory.setFeature(Constant.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = schemaFactory.newSchema(new File(pathToXsd));
        SAXParserFactory saxFactory = SAXParserFactory.newInstance();
        saxFactory.setSchema(schema);

        javax.xml.parsers.SAXParser parser = factory.newSAXParser();

        parser.parse(pathToXml, this);
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = localName;
        if (XML.PHONES.equalsTo(currentElement)) {
            phones = new Phones();
            return;
        }
        if (XML.PHONE.equalsTo(currentElement)) {
            phone = new Phone();
            InitVariable.initComplexVariable(phone);
            return;
        }
        if(XML.BATTERY_CAPACITY.equalsTo(currentElement)) {
            phone.getParameters().getBatteryCapacity().setCapacity(Capacity.valueOf(attributes.getValue(Constant.ATTRIBUTE_CAPACITY)));
            return;
        }
        if(XML.PRICE.equalsTo(currentElement)) {
            phone.getPrice().setCurrency(Currency.valueOf(attributes.getValue(Constant.ATTRIBUTE_CURRENCY)));
            return;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        String elementText = new String(ch, start, length).trim();
        if (elementText.isEmpty()) {
            return;
        }
        if (XML.PHONE_INDEX.equalsTo(currentElement)) {
            phone.setPhoneIndex(elementText);
            return;
        }
        if (XML.MODEL.equalsTo(currentElement)) {
            phone.setModel(elementText);
            return;
        }
        if (XML.DIAGONAL.equalsTo(currentElement)) {
            phone.getParameters().setDiagonal(Float.parseFloat(elementText));
            return;
        }
        if (XML.NUMBER_OF_CAMERA.equalsTo(currentElement)) {
            phone.getParameters().setNumberOfCamera(Integer.parseInt(elementText));
            return;
        }
        if (XML.BATTERY_CAPACITY.equalsTo(currentElement)) {
            phone.getParameters().getBatteryCapacity().setValue(new BigDecimal(elementText));
            return;
        }
        if (XML.PRICE.equalsTo(currentElement)) {
            phone.getPrice().setValue(new BigDecimal(elementText));
            return;
        }
        if (XML.DESCRIPTION.equalsTo(currentElement)) {
            phone.getDescription().add(elementText);
            return;
        }
        if (XML.SPECIAL_FUNCTION_1.equalsTo(currentElement)) {
            phone.getSpecialFunction().setSpecialFunction1(elementText);
            return;
        }
        if (XML.SPECIAL_FUNCTION_2.equalsTo(currentElement)) {
            phone.getSpecialFunction().setSpecialFunction2(elementText);
            return;
        }
        if (XML.SPECIAL_FUNCTION_3.equalsTo(currentElement)) {
            phone.getSpecialFunction().setSpecialFunction3(elementText);
            return;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (XML.PHONE.equalsTo(localName)) {
            phones.getPhone().add(phone);
        }
    }

    public static void main(String[] args) throws Exception {
        SAXParser saxParser = new SAXParser();
        saxParser.parse(Constant.PATH_TO_XML, Constant.PATH_TO_XSD);
        Phones phones = saxParser.getPhones();
        SaveToXML.saveToXML(phones, Constant.SAX_XML);
    }
}