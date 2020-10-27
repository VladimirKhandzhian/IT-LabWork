package ua.nure.khandzhian.it.lab2.parser;

import org.apache.commons.lang3.ObjectUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sun.misc.FloatingDecimal;
import ua.nure.khandzhian.it.lab2.Constant;
import ua.nure.khandzhian.it.lab2.entity.BatteryCapacity;
import ua.nure.khandzhian.it.lab2.entity.Parameters;
import ua.nure.khandzhian.it.lab2.entity.Phone;
import ua.nure.khandzhian.it.lab2.entity.Phones;
import ua.nure.khandzhian.it.lab2.entity.Price;
import ua.nure.khandzhian.it.lab2.entity.SpecialFunction;
import ua.nure.khandzhian.it.lab2.entity.enumeration.Capacity;
import ua.nure.khandzhian.it.lab2.entity.enumeration.Currency;
import ua.nure.khandzhian.it.lab2.util.SaveToXML;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class DOMParser {

    private Phones parsePhones(String pathToXml, String pathToXsd) throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder builder = dbf.newDocumentBuilder();
        Document document = builder.parse(new File(pathToXml));
        document.getDocumentElement().normalize();

        validate(pathToXsd, document);

        Phones phones = new Phones();
        NodeList nodes = document.getElementsByTagName(Constant.TAG_PHONE);
        for (int i = 0; i < nodes.getLength(); i++) {
            phones.getPhone().add(parsePhone(nodes.item(i)));
        }
        return phones;
    }

    private Phone parsePhone(Node node) {
        Phone phone = new Phone();
        Element element = (Element) node;

        phone.setPhoneIndex(element.getElementsByTagName(Constant.TAG_PHONE_INDEX).item(0).getTextContent());
        phone.setModel(element.getElementsByTagName(Constant.TAG_MODEL).item(0).getTextContent());
        phone.getDescription().add(element.getElementsByTagName(Constant.TAG_DESCRIPTION).item(0).getTextContent());
        phone.setParameters(parseParameter(node));
        phone.setPrice(parsePrice(node));
        phone.setSpecialFunction(parseSpecialFunction(node));

        return phone;
    }

    private Parameters parseParameter(Node node) {
        Parameters parameters = new Parameters();
        Element element = (Element) node;

        parameters.setDiagonal(FloatingDecimal.parseFloat(
                element.getElementsByTagName(Constant.TAG_DIAGONAL).item(0).getTextContent()));
        parameters.setNumberOfCamera(Integer.parseInt(
                element.getElementsByTagName(Constant.TAG_NUMBER_OF_CAMERA).item(0).getTextContent()));
        parameters.setBatteryCapacity(parseBatteryCapacity(node));

        return parameters;
    }

    private BatteryCapacity parseBatteryCapacity(Node node) {
        BatteryCapacity battery = new BatteryCapacity();
        Element element = (Element) node;

        battery.setValue(BigDecimal.valueOf(Double.parseDouble(
                element.getElementsByTagName(Constant.TAG_BATTERY_CAPACITY).item(0).getTextContent())));
        battery.setCapacity(Capacity.valueOf(element.getElementsByTagName(Constant.TAG_BATTERY_CAPACITY)
                .item(0).getAttributes().getNamedItem(Constant.ATTRIBUTE_CAPACITY).getTextContent()));

        return battery;
    }

    private Price parsePrice(Node node) {
        Price price = new Price();
        Element element = (Element) node;

        price.setValue(BigDecimal.valueOf(Double.parseDouble(element.getElementsByTagName(Constant.TAG_PRICE)
                .item(0).getTextContent())));
        price.setCurrency(Currency.valueOf(element.getElementsByTagName(Constant.TAG_PRICE)
                .item(0).getAttributes().getNamedItem(Constant.ATTRIBUTE_CURRENCY).getTextContent()));

        return price;
    }

    private SpecialFunction parseSpecialFunction(Node node) {
        SpecialFunction specialFunction = new SpecialFunction();
        Element element = (Element) node;

        Node specFunction1 = element.getElementsByTagName(Constant.TAG_SPECIAL_FUNCTION_1).item(0);
        Node specFunction2 = element.getElementsByTagName(Constant.TAG_SPECIAL_FUNCTION_2).item(0);
        Node specFunction3 = element.getElementsByTagName(Constant.TAG_SPECIAL_FUNCTION_3).item(0);

        if (ObjectUtils.isNotEmpty(specFunction1)) {
            specialFunction.setSpecialFunction1(specFunction1.getTextContent());
        } else if (ObjectUtils.isNotEmpty(specFunction2)) {
            specialFunction.setSpecialFunction2(specFunction2.getTextContent());
        } else if (ObjectUtils.isNotEmpty(specFunction3)) {
            specialFunction.setSpecialFunction3(specFunction3.getTextContent());
        }
        return specialFunction;
    }

    private void validate(String pathToXsd, Document document) throws SAXException, IOException {
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(pathToXsd));
        Validator validator = schema.newValidator();
        try {
            validator.validate(new DOMSource(document));
        } catch (SAXException exception) {
            throw new SAXException("Invalid xml --> " + exception.getMessage());
        }
    }

    public static void main(String[] args) throws Exception {
        DOMParser domParser = new DOMParser();
        Phones phones = domParser.parsePhones(Constant.PATH_TO_XML, Constant.PATH_TO_XSD);
        SaveToXML.saveToXML(phones, Constant.DOM_XML);
    }
}
