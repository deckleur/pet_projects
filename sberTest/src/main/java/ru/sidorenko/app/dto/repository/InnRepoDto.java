package ru.sidorenko.app.dto.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 *  Inn license repository dto. Contains the following information:
 *      1. cityBirth - city of birth, a frequent parameter of a document, but not found in every document
 *      2. dateAssignment - the date of assignment does not depend on the date of issue of the document
 *      The date of issue of this type of document is not indicated
 *      3. inspectorName - name of the inspector who assigned the tax number
 *
 *  This type of dto does not have a constructor with required fields,
 *  because creating an object of a class using ObjectMapper for json file reading,
 *  requires a default constructor.
 */

public class InnRepoDto extends DocumentRepoDto {

    @Getter @Setter
    private String cityBirth;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateAssignment;

    @Getter @Setter
    private String inspectorName;

}
