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
                documentControllerDto = new PassportControllerDto(
                        documentRepoDto.getId(),
                        documentRepoDto.getNumber(),
                        documentRepoDto.getLastName() + " "
                                + documentRepoDto.getFirstName() + " "
                                + documentRepoDto.getMiddleName(),
                        documentRepoDto.getDateBirth(),
                        ((PassportRepoDto) documentRepoDto).getCityBirth(),
                        ((PassportRepoDto) documentRepoDto).getDepartmentCode()
                );
                break;
            case INN:
                documentControllerDto = new InnControllerDto(
                        documentRepoDto.getId(),
                        documentRepoDto.getNumber(),
                        documentRepoDto.getLastName() + " "
                                + documentRepoDto.getFirstName() + " "
                                + documentRepoDto.getMiddleName(),
                        documentRepoDto.getDateBirth(),
                        ((InnRepoDto) documentRepoDto).getCityBirth(),
                        ((InnRepoDto) documentRepoDto).getDateAssignment(),
                        ((InnRepoDto) documentRepoDto).getInspectorName()
                );
                break;
            case DRIVER_LICENSE:
                documentControllerDto = new DriverLicenseControllerDto(
                        documentRepoDto.getId(),
                        documentRepoDto.getNumber(),
                        documentRepoDto.getLastName() + " "
                                + documentRepoDto.getFirstName() + " "
                                + documentRepoDto.getMiddleName(),
                        documentRepoDto.getDateBirth(),
                        ((DriverLicenseRepoDto) documentRepoDto).getCityBirth(),
                        ((DriverLicenseRepoDto) documentRepoDto).getDateIssue(),
                        ((DriverLicenseRepoDto) documentRepoDto).getDateExpiration(),
                        ((DriverLicenseRepoDto) documentRepoDto).getDepartment(),
                        ((DriverLicenseRepoDto) documentRepoDto).getRegistration(),
                        ((DriverLicenseRepoDto) documentRepoDto).getCategories()
                );
                break;
            default:
                throw new DocumentTypeNotFoundException();
        }
        return documentControllerDto;
    }
}
