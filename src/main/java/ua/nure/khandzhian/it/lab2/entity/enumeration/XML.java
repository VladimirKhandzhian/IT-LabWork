package ua.nure.khandzhian.it.lab2.entity.enumeration;

public enum XML {

    PHONES("Phones"),
    PHONE("Phone"),
    PHONE_INDEX("PhoneIndex"),
    MODEL("Model"),
    PARAMETERS("Parameters"),
    DIAGONAL("Diagonal"),
    NUMBER_OF_CAMERA("NumberOfCamera"),
    BATTERY_CAPACITY("BatteryCapacity"),
    PRICE("Price"),
    DESCRIPTION("Description"),
    SPECIAL_FUNCTION("SpecialFunction"),
    SPECIAL_FUNCTION_1("SpecialFunction1"),
    SPECIAL_FUNCTION_2("SpecialFunction2"),
    SPECIAL_FUNCTION_3("SpecialFunction3"),
    ATTRIBUTE_CAPACITY("capacity"),
    ATTRIBUTE_CURRENCY("currency");

    private String value;

    XML(String value) {
        this.value = value;
    }

    public boolean equalsTo(String name) {
        return value.equals(name);
    }

    public String value() {
        return value;
    }
}
