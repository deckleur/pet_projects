package ru.sidorenko.app.dto.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

/**
 *  Common controller dto for all types of documents. Contains the following information:
 *
 *      1. id - the number of the json document in the database
 *      2. number - unique identifier of the document
 *      3. fullName - includes first name, last name, patronymic
 *      4. dateBirth - date of birth format 20-02-1991
 *
 *  All fields are required.
 */

public class DocumentControllerDto {

    @Getter @Setter
    private Long id;

    @Getter @Setter
    private Long number;

    @Getter @Setter
    private String fullName;

    @Getter @Setter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date dateBirth;

    public DocumentControllerDto(Long id, Long number, String fullName, Date dateBirth) {
        this.id = id;
        this.number = number;
        this.fullName = fullName;
        this.dateBirth = dateBirth;
    }
}
