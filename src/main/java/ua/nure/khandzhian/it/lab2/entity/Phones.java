package ua.nure.khandzhian.it.lab2.entity;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "phone"
})
@XmlRootElement(name = "Phones")
public class Phones {

    @XmlElement(name = "Phone", required = true)
    protected List<Phone> phone;

    public List<Phone> getPhone() {
        if (phone == null) {
            phone = new ArrayList<>();
        }
        return this.phone;
    }

    @Override
    public String toString() {
        return "Phones{" +
                "Phone: " + phone +
                "}";
    }
}
