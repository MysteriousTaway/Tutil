package dev.taway.net.api;

public enum RequestType {
    /**
     * The GET method requests a representation of the specified resource. Requests using GET should only retrieve data.
     */
    GET,
    /**
     * The POST method submits an entity to the specified resource, often causing a change in state or side effects on the server.
     */
    POST,
    /**
     * The PUT method replaces all current representations of the target resource with the request payload.
     */
    PUT,
    /**
     * The DELETE method deletes the specified resource.
     */
    DELETE,
    /**
     * The PATCH method applies partial modifications to a resource.
     */
    PATCH,
    /**
     * The HEAD method asks for a response identical to that of a GET request, but without the response body.
     */
    HEAD,
    /**
     * The OPTIONS method describes the communication options for the target resource.
     */
    OPTIONS,
    /**
     * The TRACE method performs a message loop-back test along the path to the target resource.
     */
    TRACE
}
