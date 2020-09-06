package ru.sidorenko.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sidorenko.app.dto.controller.DocumentControllerDto;
import ru.sidorenko.app.dto.repository.DocumentRepoDto;
import ru.sidorenko.app.repository.DocumentRepository;

/**
 * Service for working with documents, has one repository constant, for reading information from json files.
 * For this, the getDocInfo method is implemented, which takes the ID and the document type as parameters.
 * Returns a new object of the class DocumentControllerDto.
 */

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository repository;

    @Autowired
    public DocumentServiceImpl(DocumentRepository repository) {
        this.repository = repository;
    }

    /**
     * Depending on the type of the requested document,
     * this method will return information about the document, finding the document by ID.
     * @param id
     * @param type
     * @return DocumentControllerDto
     */
    @Override
    public DocumentControllerDto getDocInfo(Long id, String type) {
        DocumentType documentType = DocumentType.from(type);
        DocumentRepoDto documentRepoDto = repository.readDataFromJson(id, documentType.getDirectory(), documentType.getClazz());

        return DocumentFactory.getDocument(documentType, documentRepoDto);
    }
}
