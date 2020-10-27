package ua.nure.khandzhian.it.lab2.parser;

import org.xml.sax.helpers.DefaultHandler;
import ua.nure.khandzhian.it.lab2.Constant;
import ua.nure.khandzhian.it.lab2.entity.Phone;
import ua.nure.khandzhian.it.lab2.entity.Phones;
import ua.nure.khandzhian.it.lab2.entity.enumeration.Capacity;
import ua.nure.khandzhian.it.lab2.entity.enumeration.Currency;
import ua.nure.khandzhian.it.lab2.entity.enumeration.XML;
import ua.nure.khandzhian.it.lab2.util.InitVariable;
import ua.nure.khandzhian.it.lab2.util.SaveToXML;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import javax.xml.transform.stream.StreamSource;
import java.math.BigDecimal;
import java.util.Iterator;

public class STAXParser extends DefaultHandler {

    private Phones phones;
    private Phone phone;

    public Phones getPhones() {
        return phones;
    }

    public void parse(String pathToXml) throws XMLStreamException {
        String currentElement = null;

        XMLInputFactory factory = XMLInputFactory.newInstance();
        factory.setProperty(XMLInputFactory.IS_NAMESPACE_AWARE, true);
        factory.setProperty(XMLInputFactory.IS_VALIDATING, "yes");
        XMLEventReader reader = factory.createXMLEventReader(new StreamSource(pathToXml));

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

    protected void startHandler(String currentElement) {
        if (XML.PHONES.equalsTo(currentElement)) {
            phones = new Phones();
            return;
        }
        if (XML.PHONE.equalsTo(currentElement)) {
            phone = new Phone();
            InitVariable.initComplexVariable(phone);
            return;
        }
    }

    protected void contentHandler(XMLEvent event, String currentElement) {
        Characters characters = event.asCharacters();

        if (XML.PHONE_INDEX.equalsTo(currentElement)) {
            phone.setPhoneIndex(characters.getData());
            return;
        }
        if (XML.MODEL.equalsTo(currentElement)) {
            phone.setModel(characters.getData());
            return;
        }
        if (XML.DIAGONAL.equalsTo(currentElement)) {
            phone.getParameters().setDiagonal(Float.parseFloat(characters.getData()));
            return;
        }
        if (XML.NUMBER_OF_CAMERA.equalsTo(currentElement)) {
            phone.getParameters().setNumberOfCamera(Integer.parseInt(characters.getData()));
            return;
        }
        if (XML.BATTERY_CAPACITY.equalsTo(currentElement)) {
            phone.getParameters().getBatteryCapacity().setValue(new BigDecimal(characters.getData()));
            return;
        }
        if (XML.PRICE.equalsTo(currentElement)) {
            phone.getPrice().setValue(new BigDecimal(characters.getData()));
            return;
        }
        if (XML.DESCRIPTION.equalsTo(currentElement)) {
            phone.getDescription().add(characters.getData());
            return;
        }
        if (XML.SPECIAL_FUNCTION_1.equalsTo(currentElement)) {
            phone.getSpecialFunction().setSpecialFunction1(characters.getData());
            return;
        }
        if (XML.SPECIAL_FUNCTION_2.equalsTo(currentElement)) {
            phone.getSpecialFunction().setSpecialFunction2(characters.getData());
            return;
        }
        if (XML.SPECIAL_FUNCTION_3.equalsTo(currentElement)) {
            phone.getSpecialFunction().setSpecialFunction3(characters.getData());
            return;
        }
    }

    protected void attributeHandler(XMLEvent event) {
        Iterator<Attribute> attributes = event.asStartElement().getAttributes();
        while (attributes.hasNext()) {
            Attribute attribute = attributes.next();
            if (XML.ATTRIBUTE_CAPACITY.equalsTo(attribute.getName().toString())) {
                phone.getParameters().getBatteryCapacity().setCapacity(Capacity.valueOf(attribute.getValue()));
                return;
            }
            if (XML.ATTRIBUTE_CURRENCY.equalsTo(attribute.getName().toString())) {
                phone.getPrice().setCurrency(Currency.valueOf(attribute.getValue()));
                return;
            }
        }
    }

    protected void endHandler(XMLEvent event) {
        EndElement endElement = event.asEndElement();
        String localName = endElement.getName().getLocalPart();
        if (XML.PHONE.equalsTo(localName)) {
            phones.getPhone().add(phone);
            return;
        }
    }

    public static void main(String[] args) throws Exception {
        STAXParser staxParser = new STAXParser();
        staxParser.parse(Constant.PATH_TO_XML);
        SaveToXML.saveToXML(staxParser.getPhones(), Constant.STAX_XML);
    }
}
