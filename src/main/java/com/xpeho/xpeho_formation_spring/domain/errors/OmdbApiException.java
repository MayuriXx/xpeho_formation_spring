package com.xpeho.xpeho_formation_spring.domain.errors;

/**
 * Exception thrown when the OMDb external API is unreachable or returns an error.
 * <p>
 * This unchecked exception wraps any {@link java.io.IOException} that occurs
 * during an OMDb API call, allowing it to propagate to the
 * {@link com.xpeho.xpeho_formation_spring.presentation.errors.ErrorController}
 * and be converted into a 503 HTTP response.
 *
 * @author XPEHO
 */
public class OmdbApiException extends RuntimeException {

    /**
     * Constructs an OmdbApiException wrapping the original cause.
     *
     * @param cause the original {@link java.io.IOException} thrown by the HTTP client
     */
    public OmdbApiException(Throwable cause) {
        super("L'API OMDb est inaccessible : " + cause.getMessage(), cause);
    }
}

