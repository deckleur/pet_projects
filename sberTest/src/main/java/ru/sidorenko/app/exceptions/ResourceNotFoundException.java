package ru.sidorenko.app.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception returning 404 service response status if the requested file is not found.
 */

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "File not found")
public class ResourceNotFoundException extends RuntimeException {

}
