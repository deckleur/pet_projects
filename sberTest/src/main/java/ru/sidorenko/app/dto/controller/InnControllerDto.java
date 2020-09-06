package ru.sidorenko.app.dto.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 *  Inn controller dto. Contains the following information:
 *
 *      1. cityBirth - city of birth, a frequent parameter of a document, but not found in every document
 *      2. dateAssignment - the date of assignment does not depend on the date of issue of the document
 *      The date of issue of this type of document is not indicated
 *      3. inspectorName - name of the inspector who assigned the tax number
 *
 *  As well as superclass (DocumentControllerDto) fields
 *  All fields are required.
 */

public class InnControllerDto extends DocumentControllerDto {

    @Getter @Setter
    private String cityBirth;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateAssignment;

    @Getter @Setter
    private String inspectorName;

    public InnControllerDto(Long id, Long number, String fullName, Date dateBirth, String cityBirth, Date dateAssignment, String inspectorName) {
        super(id, number, fullName, dateBirth);
        this.cityBirth = cityBirth;
        this.dateAssignment = dateAssignment;
        this.inspectorName = inspectorName;
    }
}
