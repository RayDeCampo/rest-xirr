package org.decampo.rest;

import javax.inject.Singleton;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * A handler for when our REST implementation throws {@link ServiceException}s.
 * Be aware that setting the exception type too broad can interfere with
 * OPTIONS requests for CORS.
 */
@Provider
@Singleton
public class ServiceExceptionMapper implements ExceptionMapper<ServiceException> {

    @Override
    public Response toResponse(ServiceException exception) {
        // Send the exception details to the client
        // A production version would probably use a custom error object
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
            .entity(exception)
            .build();
    }

}
