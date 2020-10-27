package ua.nure.khandzhian.it.lab2.entity.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Capacity")
@XmlEnum
public enum Capacity {

    @XmlEnumValue("Amper")
    Amper("Amper"),
    @XmlEnumValue("mAmper")
    mAmper("mAmper"),
    @XmlEnumValue("kAmper")
    kAmper("kAmper");
    private final String value;

    Capacity(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Capacity fromValue(String v) {
        for (Capacity c: Capacity.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
