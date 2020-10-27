package ua.nure.khandzhian.it.lab2.entity;

import javax.xml.bind.annotation.XmlRegistry;

@XmlRegistry
public class ObjectFactory {

    public ObjectFactory() {
    }

    public Phones createPhones() {
        return new Phones();
    }

    public Phone createPhone() {
        return new Phone();
    }

    public Parameters createParameters() {
        return new Parameters();
    }

    public BatteryCapacity createBatteryCapacity() {
        return new BatteryCapacity();
    }

    public SpecialFunction createSpecialFunction() {
        return new SpecialFunction();
    }
}
