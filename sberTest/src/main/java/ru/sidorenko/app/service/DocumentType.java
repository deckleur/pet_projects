package ru.sidorenko.app.service;

import lombok.Getter;
import ru.sidorenko.app.dto.repository.DocumentRepoDto;
import ru.sidorenko.app.dto.repository.DriverLicenseRepoDto;
import ru.sidorenko.app.dto.repository.InnRepoDto;
import ru.sidorenko.app.dto.repository.PassportRepoDto;
import ru.sidorenko.app.exceptions.DocumentTypeNotFoundException;

/**
 *  Class for typing document
 *  Has three fields:
 *      1. Type - requested document
 *      2. Directory - where json files of this type are located
 *      3. Class - extends DocumentRepoDto class
 *
 *  The "from" method returns a new DocumentType object, if the given document type exists.
 */

@Getter
public enum DocumentType {
    DRIVER_LICENSE("driverlicense", "driver_license", DriverLicenseRepoDto.class),
    PASSPORT("passport", "passport", PassportRepoDto.class),
    INN("inn", "inn", InnRepoDto.class);

    private final String type;
    private final String directory;
    private final Class<? extends DocumentRepoDto> clazz;

    DocumentType(String type, String directory, Class<? extends DocumentRepoDto> clazz) {
        this.type = type;
        this.directory = directory;
        this.clazz = clazz;
    }

    public static DocumentType from(String type){
        for(DocumentType documentType : DocumentType.values()){
            if (documentType.type.equals(type)){
                return documentType;
            }
        }
        throw new DocumentTypeNotFoundException();
    }
}
