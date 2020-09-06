package ru.sidorenko.app.dto.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 *  Driver license repository dto. Contains the following information:
 *      1. cityBirth - city of birth, a frequent parameter of a document, but not found in every document
 *      2. dateIssue - date of issue of the document, occurs often, but not in all types of documents
 *      3. dateExpiration - date when the document expires
 *      4. department - name of the gibdd department that issued the document
 *      5. registration - region of registration of the owner of the document
 *      6. categories - driving categories, from A to Tb
 *
 *  As well as superclass (DocumentRepoDto) fields
 *
 *  This type of dto does not have a constructor with required fields,
 *  because creating an object of a class using ObjectMapper for json file reading,
 *  requires a default constructor.
 */

public class DriverLicenseRepoDto extends DocumentRepoDto {

    @Getter @Setter
    private String cityBirth;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateIssue;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateExpiration;

    @Getter @Setter
    private String department;

    @Getter @Setter
    private String registration;

    @Getter @Setter
    private String categories;
}
