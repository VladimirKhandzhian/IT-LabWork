package ua.nure.khandzhian.it.lab2.entity.enumeration;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "Currency")
@XmlEnum
public enum Currency {

    UAH,
    USD,
    EUR,
    RUB,
    PLN;

    public String value() {
        return name();
    }

    public static Currency fromValue(String v) {
        return valueOf(v);
    }

}
