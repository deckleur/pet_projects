package ru.sidorenko.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *  An exception returning 404 service response status if the requested document type is not found.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Document type not found")
public class DocumentTypeNotFoundException extends RuntimeException {
}
