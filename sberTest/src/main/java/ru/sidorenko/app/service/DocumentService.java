package ru.sidorenko.app.service;

import ru.sidorenko.app.dto.controller.DocumentControllerDto;

public interface DocumentService {
    /**
     * Service method for getting document information
     *
     * @param id
     * @param type
     * @return DocumentControllerDto
     */
    DocumentControllerDto getDocInfo(Long id, String type);

}
