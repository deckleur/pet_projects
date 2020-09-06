package ru.sidorenko.app.dto.controller;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 *  Passport controller dto. Contains the following information:
 *      1. cityBirth - city of birth, a frequent parameter of a document, but not found in every document
 *      2. departmentCode - unique code of the organization that issued the passport
 *
 *  As well as superclass (DocumentControllerDto) fields
 *  All fields are required.
 */

public class PassportControllerDto extends DocumentControllerDto {

    @Getter @Setter
    private String cityBirth;

    @Getter @Setter
    private int departmentCode;

    public PassportControllerDto(Long id, Long number, String fullName, Date dateBirth, String cityBirth, int departmentCode) {
        super(id, number, fullName, dateBirth);
        this.cityBirth = cityBirth;
        this.departmentCode = departmentCode;
    }
}
