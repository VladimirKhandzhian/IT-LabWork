package ua.nure.khandzhian.it.lab2.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Phone", propOrder = {
    "phoneIndex",
    "model",
    "parameters",
    "price",
    "description",
    "specialFunction"
})
public class Phone {

    @XmlElement(name = "PhoneIndex", required = true)
    protected String phoneIndex;
    @XmlElement(name = "Model", required = true)
    protected String model;
    @XmlElement(name = "Parameters", required = true)
    protected Parameters parameters;
    @XmlElement(name = "Price", required = true)
    protected Price price;
    @XmlElement(name = "Description")
    protected List<String> description;
    @XmlElement(name = "SpecialFunction", required = true)
    protected SpecialFunction specialFunction;

    public String getPhoneIndex() {
        return phoneIndex;
    }

    public void setPhoneIndex(String value) {
        this.phoneIndex = value;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String value) {
        this.model = value;
    }

    public Parameters getParameters() {
        return parameters;
    }

    public void setParameters(Parameters value) {
        this.parameters = value;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price value) {
        this.price = value;
    }

    public List<String> getDescription() {
        if (description == null) {
            description = new ArrayList<>();
        }
        return this.description;
    }

    public SpecialFunction getSpecialFunction() {
        return specialFunction;
    }

    public void setSpecialFunction(SpecialFunction value) {
        this.specialFunction = value;
    }

    @Override
    public String toString() {
        return "Phone{" +
                "Phone Index: " + phoneIndex +
                ", Model: " + model +
                ", Parameters: " + parameters +
                ", Price: " + price +
                ", Description: " + description +
                ", SpecialFunction: " + specialFunction +
                "}";
    }
}
