package ru.sidorenko.app.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ru.sidorenko.app.exceptions.ResourceNotFoundException;
import java.io.InputStream;

/**
 *  This repository has a single method - readDataFromJson,
 *  which takes the parameters described below and returns a new document class object
 *  corresponding to the requested document type.
 */

@Service
public class DocumentRepository {

    private static final Logger logger = Logger.getLogger(DocumentRepository.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     *  A method for reading json files, and based on the received data,
     *  creating and returning the corresponding class object (? extends DocumentRepoDto).
     * @param id
     * @param directory
     * @param valueType
     * @param <T>
     * @return <T> T
     */
    public <T> T readDataFromJson(Long id, String directory, Class<T> valueType) {

        try(InputStream fileStream = getClass().getResourceAsStream(String.format("/json/documents/%s/%d.json", directory, id))) {

            return mapper.readValue(fileStream, valueType);

        }catch (Exception e) {
            logger.error("Error while trying to read data from json: " + e.getMessage(), e);
            throw new ResourceNotFoundException();
        }
    }
}
