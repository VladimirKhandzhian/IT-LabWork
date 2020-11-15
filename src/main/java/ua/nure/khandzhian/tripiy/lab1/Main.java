package ua.nure.khandzhian.tripiy.lab1;

import ua.nure.khandzhian.tripiy.lab1.entity.MaximumGeneralizedUtility;
import ua.nure.khandzhian.tripiy.lab1.entity.Phone;
import ua.nure.khandzhian.tripiy.lab1.metods.CompromiseArea;
import ua.nure.khandzhian.tripiy.lab1.metods.MainCriterion;
import ua.nure.khandzhian.tripiy.lab1.metods.UtilityFunction;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        List<Phone> phones = new ArrayList<>();
        main.initPhoneList(phones);

        System.out.println("Pareto area --> " + CompromiseArea.pareto(phones));
        System.out.println("Sleiter area --> " + CompromiseArea.sleiter(phones));
        main.printMainCriterionPrincipe(phones);
        main.printFunctionalPriceAnalysis(phones);
        main.printUtilityFunction(phones);
        main.printMaximumGeneralizationUtilities(phones);
    }

    public void initPhoneList(List<Phone> phones) {
        phones.add(new Phone(1, "Huawei Y7 2019 3/32 GB Midnight Black", 13, 6.3, 3599.0));
        phones.add(new Phone(2, "Nokia 5.3 4/64GB DualSim Charcoal", 13, 6.6, 3999.0));
        phones.add(new Phone(3, "Vivo Y11 3/32 GB Agate Red", 15, 6.4, 3699.0));
        phones.add(new Phone(4, "ZTE Blade 20 Smart 4/128GB Gradient Green", 16, 6.5, 4599.0));
        phones.add(new Phone(5, "HUAWEI P smart Z 4/64GB Emerald Green(51093WVK)", 16, 6.6, 4999.0));
        phones.add(new Phone(6, "ZTE BLADE V2020 Smart 4/64 GB Blue", 16, 6.8, 4199.0));
        phones.add(new Phone(7, "Lenovo S5 Pro 6/64Gb Blue", 20, 6.2, 3899.0));
        phones.add(new Phone(8, "Xiaomi Redmi Note 9 4/128GB Midnight Grey", 48, 6.4, 5000.0));
        phones.add(new Phone(9, "Motorola E7 Plus 4/64GB Blue (PAKX0008RS)", 48, 6.5, 4099.0));
        phones.add(new Phone(10, "Realme 6i 4/128Gb Green", 48, 6.5, 4799.0));
    }

    public void printMainCriterionPrincipe(List<Phone> phones) {
        System.out.println("=================================Main criterion principe=====================================");

        System.out.println("===================================Main criterion price======================================");
        List<Phone> criterionPrice = MainCriterion.filterPhonesByCondition(phones,
                phone -> phone.getPowerOfCamera() >= 16 && phone.getDiagonal() >= 6.5);
        System.out.println(criterionPrice);
        System.out.println("===================================Best phone======================================");
        System.out.println(MainCriterion.getPhoneWithMinPrice(criterionPrice));

        System.out.println("===================================Main criterion diagonal======================================");
        List<Phone> criterionDiagonal = MainCriterion.filterPhonesByCondition(phones,
                phone -> phone.getPowerOfCamera() >= 16 && phone.getPrice() <= 4500);
        System.out.println(criterionDiagonal);
        System.out.println("===================================Best phone======================================");
        System.out.println(MainCriterion.getPhoneWithMaxDiagonal(criterionDiagonal));

        System.out.println("===================================Main criterion camera======================================");
        List<Phone> criterionCamera = MainCriterion.filterPhonesByCondition(phones,
                phone -> phone.getDiagonal() >= 6.5 && phone.getPrice() <= 4500);
        System.out.println(criterionCamera);
        System.out.println("===================================Best phone======================================");
        System.out.println(MainCriterion.getPhoneWithMaxCamera(criterionCamera));
    }

    public void printFunctionalPriceAnalysis(List<Phone> phones) {
        System.out.println("=================================Functional-price analysis=====================================");

        System.out.println("=================================Filter by diagonal=====================================");
        List<Phone> analysisByDiagonal = MainCriterion.filterPhonesByCondition(phones,
                phone -> phone.getDiagonal() >= 6.5);
        System.out.println(analysisByDiagonal);
        System.out.println("====================================Table 1.1=========================================");
        analysisByDiagonal.forEach(phone -> {
            double result = phone.getPowerOfCamera() / phone.getPrice();
            System.out.println("k1/k3 for phone " + phone.getId() + " --> " + result);
        });

        System.out.println("=================================Filter by camera=====================================");
        List<Phone> analysisByCamera = MainCriterion.filterPhonesByCondition(phones,
                phone -> phone.getPowerOfCamera() >= 16);
        System.out.println(analysisByCamera);
        System.out.println("====================================Table 1.2=========================================");
        analysisByCamera.forEach(phone -> {
            double result = phone.getDiagonal() / phone.getPrice();
            System.out.println("k2/k3 for phone " + phone.getId() + " --> " + result);
        });
    }

    public void printUtilityFunction(List<Phone> phones) {
        System.out.println("====================================Utility function=========================================");
        System.out.println("Min camera --> " + UtilityFunction.getMinCamera(phones) +
                " Max camera --> " + UtilityFunction.getMaxCamera(phones));
        System.out.println("Min diagonal --> " + UtilityFunction.getMinDiagonal(phones) +
                " Max camera --> " + UtilityFunction.getMaxDiagonal(phones));
        System.out.println("Min price --> " + UtilityFunction.getMinPrice(phones) +
                " Max price --> " + UtilityFunction.getMaxPrice(phones));
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s | %30s | %30s | %30s", "PHONE ID", "P1", "P2", "P3");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------");
        UtilityFunction.getLocalUtility(phones).forEach(
                localUtility -> {
                    System.out.format("%10s | %30s | %30s | %30s",
                            localUtility.getID(), localUtility.getP1(), localUtility.getP2(), localUtility.getP3());
                    System.out.println();
                }
        );
        System.out.println("-------------------------------------------------------------------------------------------------------------");
    }

    public void printMaximumGeneralizationUtilities(List<Phone> phones) {
        List<MaximumGeneralizedUtility> maximumGeneralizedUtilities = UtilityFunction.getMaximumGeneralizedUtilities(phones);
        System.out.println("=========================Maximum generalization utilities function=======================");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%10s | %25s | %25s | %25s | %25s", "PHONE ID", "a1=a2=a3=1/3",
                "a1=0.3 a2=0.5 a3=0.2", "a1=0.6 a2=0.2 a3=0.2", "a1=0.2 a2=0.1 a3=0.7");
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        maximumGeneralizedUtilities.forEach(
                maximumGeneralizedUtility -> {
                    System.out.format("%10s | %25s | %25s | %25s | %25s", maximumGeneralizedUtility.getID(),
                            maximumGeneralizedUtility.getMgu1(), maximumGeneralizedUtility.getMgu2(),
                            maximumGeneralizedUtility.getMgu3(), maximumGeneralizedUtility.getMgu4());
                    System.out.println();
                }
        );
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
        System.out.format("%10s | %25s | %25s | %25s | %25s", "Pmax", UtilityFunction.getMaxMGU1(maximumGeneralizedUtilities),
                UtilityFunction.getMaxMGU2(maximumGeneralizedUtilities), UtilityFunction.getMaxMGU3(maximumGeneralizedUtilities),
                UtilityFunction.getMaxMGU4(maximumGeneralizedUtilities));
        System.out.println();
        System.out.println("----------------------------------------------------------------------------------------------------------------------------");
    }
}
