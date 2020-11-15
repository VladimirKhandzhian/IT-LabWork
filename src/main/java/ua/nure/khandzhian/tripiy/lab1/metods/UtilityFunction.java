package ua.nure.khandzhian.tripiy.lab1.metods;

import ua.nure.khandzhian.tripiy.lab1.entity.LocalUtility;
import ua.nure.khandzhian.tripiy.lab1.entity.MaximumGeneralizedUtility;
import ua.nure.khandzhian.tripiy.lab1.entity.Phone;

import java.util.ArrayList;
import java.util.List;

public class UtilityFunction {

    public static double getMinCamera(List<Phone> phones) {
        return phones.stream().mapToInt(phone -> phone.getPowerOfCamera()).min().getAsInt();
    }

    public static double getMaxCamera(List<Phone> phones) {
        return phones.stream().mapToInt(phone -> phone.getPowerOfCamera()).max().getAsInt();
    }

    public static double getMinDiagonal(List<Phone> phones) {
        return phones.stream().mapToDouble(phone -> phone.getDiagonal()).min().getAsDouble();
    }

    public static double getMaxDiagonal(List<Phone> phones) {
        return phones.stream().mapToDouble(phone -> phone.getDiagonal()).max().getAsDouble();
    }

    public static double getMinPrice(List<Phone> phones) {
        return phones.stream().mapToDouble(phone -> phone.getPrice()).min().getAsDouble();
    }

    public static double getMaxPrice(List<Phone> phones) {
        return phones.stream().mapToDouble(phone -> phone.getPrice()).max().getAsDouble();
    }

    public static List<LocalUtility> getLocalUtility(List<Phone> phones) {
        List<LocalUtility> utilities = new ArrayList<>();
        double minK1 = getMinCamera(phones);
        double maxK1 = getMaxCamera(phones);
        double minK2 = getMinDiagonal(phones);
        double maxK2 = getMaxDiagonal(phones);
        double minK3 = getMaxPrice(phones);
        double maxK3 = getMinPrice(phones);

        phones.forEach(phone -> {
            LocalUtility localUtility = new LocalUtility();
            localUtility.setID(phone.getId());
            localUtility.setP1((phone.getPowerOfCamera() - minK1) / (maxK1 - minK1));
            localUtility.setP2((phone.getDiagonal() - minK2) / (maxK2 - minK2));
            localUtility.setP3((phone.getPrice() - minK3) / (maxK3 - minK3));
            utilities.add(localUtility);
        });
        return utilities;
    }

    public static double getMaxMGU1(List<MaximumGeneralizedUtility> list) {
        return list.stream().mapToDouble(mgu -> mgu.getMgu1()).max().getAsDouble();
    }

    public static double getMaxMGU2(List<MaximumGeneralizedUtility> list) {
        return list.stream().mapToDouble(mgu -> mgu.getMgu2()).max().getAsDouble();
    }

    public static double getMaxMGU3(List<MaximumGeneralizedUtility> list) {
        return list.stream().mapToDouble(mgu -> mgu.getMgu3()).max().getAsDouble();
    }

    public static double getMaxMGU4(List<MaximumGeneralizedUtility> list) {
        return list.stream().mapToDouble(mgu -> mgu.getMgu4()).max().getAsDouble();
    }

    public static List<MaximumGeneralizedUtility> getMaximumGeneralizedUtilities(List<Phone> phones) {
        List<MaximumGeneralizedUtility> maximumGeneralizedUtilities = new ArrayList<>();
        UtilityFunction.getLocalUtility(phones).forEach(
                localUtility -> {
                    MaximumGeneralizedUtility maximumGeneralizedUtility = new MaximumGeneralizedUtility();
                    maximumGeneralizedUtility.setID(localUtility.getID());
                    maximumGeneralizedUtility.setMgu1(localUtility.getP1() * 1/3 + localUtility.getP2() * 1/3 + localUtility.getP3() * 1/3);
                    maximumGeneralizedUtility.setMgu2(localUtility.getP1() * 0.3 + localUtility.getP2() * 0.5 + localUtility.getP3() * 0.2);
                    maximumGeneralizedUtility.setMgu3(localUtility.getP1() * 0.6 + localUtility.getP2() * 0.2 + localUtility.getP3() * 0.2);
                    maximumGeneralizedUtility.setMgu4(localUtility.getP1() * 0.2 + localUtility.getP2() * 0.1 + localUtility.getP3() * 0.7);
                    maximumGeneralizedUtilities.add(maximumGeneralizedUtility);
                }
        );
        return maximumGeneralizedUtilities;
    }
}
