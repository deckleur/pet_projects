package ru.sidorenko.app.service;

import org.springframework.stereotype.Service;
import ru.sidorenko.app.dto.controller.DocumentControllerDto;
import ru.sidorenko.app.dto.controller.DriverLicenseControllerDto;
import ru.sidorenko.app.dto.controller.InnControllerDto;
import ru.sidorenko.app.dto.controller.PassportControllerDto;
import ru.sidorenko.app.dto.repository.DocumentRepoDto;
import ru.sidorenko.app.dto.repository.DriverLicenseRepoDto;
import ru.sidorenko.app.dto.repository.InnRepoDto;
import ru.sidorenko.app.dto.repository.PassportRepoDto;
import ru.sidorenko.app.exceptions.DocumentTypeNotFoundException;

/**
 * Implementation of the Factory pattern using the example of three types of document:
 * 1) Passport
 * 2) Inn
 * 3) Driver license
 */

@Service
public class DocumentFactory {

    public static DocumentControllerDto getDocument(DocumentType type, DocumentRepoDto documentRepoDto) {
        DocumentControllerDto documentControllerDto = null;
        switch (type) {
            case PASSPORT:
                PassportRepoDto passportRepoDto = (PassportRepoDto) documentRepoDto;
                documentControllerDto = new PassportControllerDto(
                        passportRepoDto.getId(),
                        passportRepoDto.getNumber(),
                        passportRepoDto.getLastName() + " "
                                + passportRepoDto.getFirstName() + " "
                                + passportRepoDto.getMiddleName(),
                        passportRepoDto.getDateBirth(),
                        passportRepoDto.getCityBirth(),
                        passportRepoDto.getDepartmentCode()
                );
                break;
            case INN:
                InnRepoDto innRepoDto = (InnRepoDto) documentRepoDto;
                documentControllerDto = new InnControllerDto(
                        innRepoDto.getId(),
                        innRepoDto.getNumber(),
                        innRepoDto.getLastName() + " "
                                + innRepoDto.getFirstName() + " "
                                + innRepoDto.getMiddleName(),
                        innRepoDto.getDateBirth(),
                        innRepoDto.getCityBirth(),
                        innRepoDto.getDateAssignment(),
                        innRepoDto.getInspectorName()
                );
                break;
            case DRIVER_LICENSE:
                DriverLicenseRepoDto driverLicenseRepoDto = (DriverLicenseRepoDto) documentRepoDto;
                documentControllerDto = new DriverLicenseControllerDto(
                        driverLicenseRepoDto.getId(),
                        driverLicenseRepoDto.getNumber(),
                        driverLicenseRepoDto.getLastName() + " "
                                + driverLicenseRepoDto.getFirstName() + " "
                                + driverLicenseRepoDto.getMiddleName(),
                        driverLicenseRepoDto.getDateBirth(),
                        driverLicenseRepoDto.getCityBirth(),
                        driverLicenseRepoDto.getDateIssue(),
                        driverLicenseRepoDto.getDateExpiration(),
                        driverLicenseRepoDto.getDepartment(),
                        driverLicenseRepoDto.getRegistration(),
                        driverLicenseRepoDto.getCategories()
                );
                break;
            default:
                throw new DocumentTypeNotFoundException();
        }
        return documentControllerDto;
    }
}
