package ru.sidorenko.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.sidorenko.app.dto.controller.DocumentControllerDto;
import ru.sidorenko.app.service.DocumentService;

/**
 *  The controller accepts api requests of the form:
 *      1. {url}/passport/{id}
 *      2. {url}/inn/{id}
 *      3. {url}/driverlicense/{id}
 *  and returns a common, own dto for each type of document.
 *
 *  By default, the response to any GET request returns a message about the structure of api requests
 *  the method defaultResponse()
 */

@RestController
public class DocumentController {

    private final DocumentService documentService;

    @Autowired
    public DocumentController (DocumentService documentService){
        this.documentService = documentService;
    }

    @GetMapping
    public String defaultResponse(){
        return "API Structure: "+
                "1. /passport/{id} "+
                "2. /inn/{id} "+
                "3. /driverlicense/{id}";
    }

    @GetMapping("/{type}/{id}")
    public DocumentControllerDto getDoc(@PathVariable(value = "id") Long id, @PathVariable(value = "type") String type){
        return documentService.getDocInfo(id, type);
    }
}
