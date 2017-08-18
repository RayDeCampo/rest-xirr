package org.decampo.rest;

import java.io.IOException;
import javax.ws.rs.HttpMethod;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

/**
 * A filter on REST responses which ensures that the proper headers are set in
 * the response for CORS.
 */
@Provider
public class CorsHeadersFilter implements ContainerResponseFilter {

    // Headers not required to be listed: for CORS
    //     https://fetch.spec.whatwg.org/#cors-safelisted-request-header
    // Also possible to set this to a custom header or "*"
    private static final String ALLOWED_HEADERS = String.join(", ",
        HttpHeaders.AUTHORIZATION,
        HttpHeaders.CONTENT_TYPE);

    private static final String ALLOWED_METHODS = String.join(", ",
        HttpMethod.DELETE,
        HttpMethod.GET,
        HttpMethod.HEAD,
        HttpMethod.OPTIONS,
        HttpMethod.POST,
        HttpMethod.PUT);

    @Override
    public void filter(
        final ContainerRequestContext requestContext,
        final ContainerResponseContext responseContext) throws IOException {
        final MultivaluedMap<String, Object> headers =
            responseContext.getHeaders();
        // Following allows for all clients
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Headers", ALLOWED_HEADERS);
        headers.add("Access-Control-Allow-Credentials", "true");
        headers.add("Access-Control-Allow-Methods", ALLOWED_METHODS);
        // Set the length of time in seconds the preflight check may be cached
        // Use 1 second here for development purposes
        headers.add("Access-Control-Max-Age", "1");
    }
}
