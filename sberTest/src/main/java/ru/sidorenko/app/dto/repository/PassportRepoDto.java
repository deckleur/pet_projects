package ru.sidorenko.app.dto.repository;

import lombok.Getter;
import lombok.Setter;

/**
 *  Passport license repository dto. Contains the following information:
 *      1. cityBirth - city of birth, a frequent parameter of a document, but not found in every document
 *      2. departmentCode - unique code of the organization that issued the passport
 *
 *  This type of dto does not have a constructor with required fields,
 *  because creating an object of a class using ObjectMapper for json file reading,
 *  equires a default constructor.
 */

public class PassportRepoDto extends DocumentRepoDto {

    @Getter @Setter
    private String cityBirth;

    @Getter @Setter
    private int departmentCode;

}
