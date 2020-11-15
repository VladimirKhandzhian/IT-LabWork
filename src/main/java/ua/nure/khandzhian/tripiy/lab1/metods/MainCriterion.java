package ua.nure.khandzhian.tripiy.lab1.metods;

import ua.nure.khandzhian.tripiy.lab1.entity.Phone;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainCriterion {

    public static List<Phone> filterPhonesByCondition(List<Phone> phones, Predicate<Phone> predicate) {
        return phones.stream().filter(predicate).collect(Collectors.toList());
    }

    public static Phone getPhoneWithMinPrice(List<Phone> phones) {
        Double minPrice = phones.stream().mapToDouble(phone -> phone.getPrice()).min().getAsDouble();
        return phones.stream().filter(phone -> phone.getPrice() == minPrice).findFirst().get();
    }

    public static Phone getPhoneWithMaxDiagonal(List<Phone> phones) {
        Double maxDiagonal = phones.stream().mapToDouble(phone -> phone.getDiagonal()).max().getAsDouble();
        return phones.stream().filter(phone -> phone.getDiagonal() == maxDiagonal).findFirst().get();
    }

    public static Phone getPhoneWithMaxCamera(List<Phone> phones) {
        Double maxCamera = phones.stream().mapToDouble(phone -> phone.getPowerOfCamera()).max().getAsDouble();
        return phones.stream().filter(phone -> phone.getPowerOfCamera() == maxCamera).findFirst().get();
    }
}
