package ua.nure.khandzhian.it.lab2.builder;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

public class SpecialFunctionBuilder {

    private StringBuilder builder = new StringBuilder();

    public SpecialFunctionBuilder append(Object value) {
        if (ObjectUtils.isNotEmpty(value)) {
            builder.append(value).append(StringUtils.SPACE);
        }
        return this;
    }

    public String build() {
        return builder.toString();
    }
}
