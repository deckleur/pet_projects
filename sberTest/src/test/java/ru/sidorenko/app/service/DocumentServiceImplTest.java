package ru.sidorenko.app.service;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import ru.sidorenko.app.config.SpringConfig;
import ru.sidorenko.app.dto.controller.DocumentControllerDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
@WebAppConfiguration
public class DocumentServiceImplTest extends TestCase {

    @Autowired
    private DocumentServiceImpl service;

    @Test
    public void getPassportInfo() {
        DocumentControllerDto documentControllerDto = service.getDocInfo(1L, "passport");

        Boolean result = documentControllerDto.getFullName().isEmpty();
        Boolean expResult = false;

        Long resultId = documentControllerDto.getId();
        Long expResultId = 1L;

        assertEquals(expResult, result);
        assertEquals(expResultId, resultId);
    }

    @Test
    public void getInnInfo() {
        DocumentControllerDto documentControllerDto = service.getDocInfo(1L, "inn");

        Boolean result = documentControllerDto.getFullName().isEmpty();
        Boolean expResult = false;

        assertEquals(expResult, result);
        assertEquals(
                "InnControllerDto",
                documentControllerDto.getClass().getSimpleName()
        );
    }

    @Test
    public void getDriverLicenseInfo(){
        DocumentControllerDto documentControllerDto = service.getDocInfo(1L, "driverlicense");
        assertEquals(
                "DriverLicenseControllerDto",
                documentControllerDto.getClass().getSimpleName()
        );
    }
}