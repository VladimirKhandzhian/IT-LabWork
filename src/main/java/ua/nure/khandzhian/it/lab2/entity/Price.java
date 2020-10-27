package ua.nure.khandzhian.it.lab2.entity;

import ua.nure.khandzhian.it.lab2.entity.enumeration.Currency;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Price", propOrder = {
    "value"
})
public class Price {

    @XmlValue
    protected BigDecimal value;
    @XmlAttribute(name = "currency", required = true)
    protected Currency currency;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency value) {
        this.currency = value;
    }

    @Override
    public String toString() {
        return "Price{" +
                "Value: " + value +
                ", Currency: " + currency +
                "}";
    }
}
