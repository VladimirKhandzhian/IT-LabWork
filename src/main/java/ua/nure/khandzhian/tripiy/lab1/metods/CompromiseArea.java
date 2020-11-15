package ua.nure.khandzhian.tripiy.lab1.metods;

import ua.nure.khandzhian.tripiy.lab1.entity.Phone;

import java.util.ArrayList;
import java.util.List;

public class CompromiseArea {

    public static List<Phone> pareto(List<Phone> phones) {
        List<Phone> pareto = new ArrayList<>(phones);
        phones.forEach(phone1 -> phones.forEach(phone2 -> {
            if(phone1.getPrice() <= phone2.getPrice() && phone1.getDiagonal() >= phone2.getDiagonal()
                && phone1.getPowerOfCamera() >= phone2.getPowerOfCamera()) {
                if(!phone1.equals(phone2)) {
                    pareto.remove(phone2);
                }
            }
        }));
        return pareto;
    }

    public static List<Phone> sleiter(List<Phone> phones) {
        List<Phone> sleiter = new ArrayList<>(phones);
        phones.forEach(phone1 -> phones.forEach(phone2 -> {
            if(phone1.getPrice() < phone2.getPrice() && phone1.getDiagonal() > phone2.getDiagonal()
                    && phone1.getPowerOfCamera() > phone2.getPowerOfCamera()) {
                if(!phone1.equals(phone2)) {
                    sleiter.remove(phone2);
                }
            }
        }));
        return sleiter;
    }
}
