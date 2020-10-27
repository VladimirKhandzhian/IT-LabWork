package ua.nure.khandzhian.it.lab2.util;

import org.apache.commons.lang3.ObjectUtils;
import ua.nure.khandzhian.it.lab2.entity.BatteryCapacity;
import ua.nure.khandzhian.it.lab2.entity.Parameters;
import ua.nure.khandzhian.it.lab2.entity.Phone;
import ua.nure.khandzhian.it.lab2.entity.Price;
import ua.nure.khandzhian.it.lab2.entity.SpecialFunction;

public class InitVariable {

    public static void initComplexVariable(Phone phone) {
        if (ObjectUtils.isEmpty(phone.getParameters())) {
            phone.setParameters(new Parameters());
            phone.getParameters().setBatteryCapacity(new BatteryCapacity());
        }
        if (ObjectUtils.isEmpty(phone.getPrice())) {
            phone.setPrice(new Price());
        }
        if (ObjectUtils.isEmpty(phone.getSpecialFunction())) {
            phone.setSpecialFunction(new SpecialFunction());
        }
    }
}
