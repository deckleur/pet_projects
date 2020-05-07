package ru.axetta.russpass.persistence.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class CustomLogTypeConverter implements AttributeConverter<CustomLogType, String> {

    @Override
    public String convertToDatabaseColumn(CustomLogType customLogType) {
        if (customLogType == null) {
            return null;
        }
        return customLogType.getCode();
    }

    @Override
    public CustomLogType convertToEntityAttribute(String code) {
        if (code == null) {
            return null;
        }

        return Stream.of(CustomLogType.values())
                .filter(c -> c.getCode().equals(code))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
