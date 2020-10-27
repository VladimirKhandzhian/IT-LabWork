package ua.nure.khandzhian.it.lab2.entity;

import ua.nure.khandzhian.it.lab2.builder.SpecialFunctionBuilder;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SpecialFunction", propOrder = {
    "specialFunction1",
    "specialFunction2",
    "specialFunction3"
})
public class SpecialFunction {

    @XmlElement(name = "SpecialFunction1")
    protected Object specialFunction1;
    @XmlElement(name = "SpecialFunction2")
    protected Object specialFunction2;
    @XmlElement(name = "SpecialFunction3")
    protected Object specialFunction3;

    public Object getSpecialFunction1() {
        return specialFunction1;
    }

    public void setSpecialFunction1(Object value) {
        this.specialFunction1 = value;
    }

    public Object getSpecialFunction2() {
        return specialFunction2;
    }

    public void setSpecialFunction2(Object value) {
        this.specialFunction2 = value;
    }

    public Object getSpecialFunction3() {
        return specialFunction3;
    }

    public void setSpecialFunction3(Object value) {
        this.specialFunction3 = value;
    }

    @Override
    public String toString() {
        SpecialFunctionBuilder builder = new SpecialFunctionBuilder();
        builder.append(specialFunction1);
        builder.append(specialFunction2);
        builder.append(specialFunction3);
        return builder.build();
    }
}
