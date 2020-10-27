package ua.nure.khandzhian.it.lab2.entity;

import ua.nure.khandzhian.it.lab2.entity.enumeration.Capacity;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BatteryCapacity", propOrder = {
    "value"
})
public class BatteryCapacity {

    @XmlValue
    protected BigDecimal value;
    @XmlAttribute(name = "capacity")
    protected Capacity capacity;

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public Capacity getCapacity() {
        if (capacity == null) {
            return Capacity.Amper;
        } else {
            return capacity;
        }
    }

    public void setCapacity(Capacity value) {
        this.capacity = value;
    }

    @Override
    public String toString() {
        return "BatteryCapacity {" +
                " Value: " + value +
                ", Capacity: " + capacity +
                "}";
    }
}
