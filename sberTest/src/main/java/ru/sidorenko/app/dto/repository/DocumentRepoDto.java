package ru.sidorenko.app.dto.repository;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 *  Common repository dto for all types of documents. Contains the following information:
 *      1. id - the number of the json document in the repository
 *      2. number - unique identifier of the document
 *      3. firstName - first name (ex. Ivan)
 *      4. middleName - middle name (ex. Ivanovich)
 *      5. lastName - last name (ex. Ivanov)
 *      6. dateBirth - date of birth format 20-02-1991
 *
 *   This type of dto does not have a constructor with required fields,
 *   because creating an object of a class using ObjectMapper for json file reading,
 *   requires a default constructor.
 */

public class DocumentRepoDto {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Long number;

    @Getter @Setter
    private String firstName;

    @Getter @Setter
    private String middleName;

    @Getter @Setter
    private String lastName;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateBirth;

}
