package ru.sidorenko.app.controllers;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.sidorenko.app.config.SpringConfig;
import ru.sidorenko.app.dto.controller.DocumentControllerDto;
import ru.sidorenko.app.exceptions.DocumentTypeNotFoundException;
import ru.sidorenko.app.exceptions.ResourceNotFoundException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@WebAppConfiguration
public class DocumentControllerTest extends TestCase {

    private Long id;

    private String type;

    private DocumentControllerDto documentControllerDto;

    @Autowired
    private DocumentController documentController;

    @Before
    public void setUpTest(){
        id = 1L;
        type = "passport";
    }

    @Test (timeout = 5000)
    public void defaultResponse() {
        String result = documentController.defaultResponse();
        String expResult = "API Structure: "+
                "1. /passport/{id} "+
                "2. /inn/{id} "+
                "3. /driverlicense/{id}";

        assertEquals("Wrong default response in DocumentController",expResult,result);
    }

    @Test(timeout = 5000)
    public void getDoc(){
        documentControllerDto = documentController.getDoc(id, type);

        Long result = documentControllerDto.getId();
        Long expResult = id;

        assertEquals("Wrong getDoc(), non expected ID in DocumentController",expResult, result);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void getDocExpectedResourceNotFoundException(){
        documentControllerDto = documentController.getDoc(0L, type);
    }

    @Test(expected = DocumentTypeNotFoundException.class)
    public void getDocumentTypeNotFoundException(){
        documentControllerDto = documentController.getDoc(1L, "none");
    }
}