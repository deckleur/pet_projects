package ru.sidorenko.app.dto.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 *  Driver license controller dto. Contains the following information:
 *
 *      1. cityBirth - city of birth, a frequent parameter of a document, but not found in every document
 *      2. dateIssue - date of issue of the document, occurs often, but not in all types of documents
 *      3. dateExpiration - date when the document expires
 *      4. department - name of the gibdd department that issued the document
 *      5. registration - region of registration of the owner of the document
 *      6. categories - driving categories, from A to Tb
 *
 *  As well as superclass (DocumentControllerDto) fields
 *  All fields are required.
 */

public class DriverLicenseControllerDto extends DocumentControllerDto {

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

    public DriverLicenseControllerDto(Long id, Long number, String fullName, Date dateBirth, String cityBirth, Date dateIssue, Date dateExpiration, String department, String registration, String categories) {
        super(id, number, fullName, dateBirth);
        this.cityBirth = cityBirth;
        this.dateIssue = dateIssue;
        this.dateExpiration = dateExpiration;
        this.department = department;
        this.registration = registration;
        this.categories = categories;
    }

}
