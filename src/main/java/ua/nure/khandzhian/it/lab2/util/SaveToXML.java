package ua.nure.khandzhian.it.lab2.util;

import org.apache.commons.lang3.ObjectUtils;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import ua.nure.khandzhian.it.lab2.Constant;
import ua.nure.khandzhian.it.lab2.entity.Phone;
import ua.nure.khandzhian.it.lab2.entity.Phones;
import ua.nure.khandzhian.it.lab2.entity.enumeration.XML;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

public class SaveToXML {

    public static void saveToXML(Phones phones, String xmlFileName) throws TransformerException, ParserConfigurationException {
        saveToXML(getDocument(phones), xmlFileName);
    }

    private static void saveToXML(Document document, String xmlFileName) throws TransformerException {
        StreamResult result = new StreamResult(new File(xmlFileName));
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(new DOMSource(document), result);
    }

    private static Document getDocument(Phones phones) throws ParserConfigurationException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        documentBuilderFactory.setNamespaceAware(true);

        Document document = documentBuilderFactory.newDocumentBuilder().newDocument();

        Element phonesElement = document.createElement(XML.PHONES.value());

        document.appendChild(phonesElement);

        for (Phone phone : phones.getPhone()) {
            Element phoneElement = document.createElement(XML.PHONE.value());
            phonesElement.appendChild(phoneElement);

            Element phoneIndexElement = document.createElement(XML.PHONE_INDEX.value());
            phoneIndexElement.setTextContent(phone.getPhoneIndex());
            phoneElement.appendChild(phoneIndexElement);

            Element phoneModelElement = document.createElement(XML.MODEL.value());
            phoneModelElement.setTextContent(phone.getPhoneIndex());
            phoneElement.appendChild(phoneModelElement);

            Element phoneParameters = document.createElement(XML.PARAMETERS.value());
            setElementParameters(phoneParameters, phone, document);
            phoneElement.appendChild(phoneParameters);

            Element phonePrice = document.createElement(XML.PRICE.value());
            phonePrice.setTextContent(phone.getPrice().getValue().toString());
            phonePrice.setAttribute(Constant.ATTRIBUTE_CURRENCY, phone.getPrice().getCurrency().value());
            phoneElement.appendChild(phonePrice);

            Element phoneDescription = document.createElement(XML.DESCRIPTION.value());
            phoneDescription.setTextContent(phone.getDescription().toString());
            phoneElement.appendChild(phoneDescription);

            Element phoneSpecialFunction = document.createElement(XML.SPECIAL_FUNCTION.value());
            setSpecialFunction(phoneSpecialFunction, phone, document);
            phoneElement.appendChild(phoneSpecialFunction);
        }
        return document;
    }

    private static void setElementParameters(Element phoneParameters, Phone phone, Document document) {
        Element diagonal = document.createElement(XML.DIAGONAL.value());
        diagonal.setTextContent(Float.toString(phone.getParameters().getDiagonal()));
        phoneParameters.appendChild(diagonal);

        Element numOfCamera = document.createElement(XML.NUMBER_OF_CAMERA.value());
        numOfCamera.setTextContent(Integer.toString(phone.getParameters().getNumberOfCamera()));
        phoneParameters.appendChild(numOfCamera);

        Element batteryCapacity = document.createElement(XML.BATTERY_CAPACITY.value());
        batteryCapacity.setTextContent(phone.getParameters().getBatteryCapacity().getValue().toString());
        batteryCapacity.setAttribute(Constant.ATTRIBUTE_CAPACITY,
                phone.getParameters().getBatteryCapacity().getCapacity().value());
        phoneParameters.appendChild(batteryCapacity);
    }

    private static void setSpecialFunction(Element phoneSpecialFunction, Phone phone, Document document) {

        if (ObjectUtils.isNotEmpty(phone.getSpecialFunction().getSpecialFunction1())) {

            Element specialFunction1 = document.createElement(XML.SPECIAL_FUNCTION_1.value());
            specialFunction1.setTextContent(phone.getSpecialFunction().getSpecialFunction1().toString());
            phoneSpecialFunction.appendChild(specialFunction1);

        } else if (ObjectUtils.isNotEmpty(phone.getSpecialFunction().getSpecialFunction2())) {

            Element specialFunction2 = document.createElement(XML.SPECIAL_FUNCTION_2.value());
            specialFunction2.setTextContent(phone.getSpecialFunction().getSpecialFunction2().toString());
            phoneSpecialFunction.appendChild(specialFunction2);

        } else if (ObjectUtils.isNotEmpty(phone.getSpecialFunction().getSpecialFunction3())) {

            Element specialFunction3 = document.createElement(XML.SPECIAL_FUNCTION_3.value());
            specialFunction3.setTextContent(phone.getSpecialFunction().getSpecialFunction3().toString());
            phoneSpecialFunction.appendChild(specialFunction3);

        }
    }
}
