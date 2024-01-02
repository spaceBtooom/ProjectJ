package com.spring.web.api.backend;

public interface GenericMapper<Request, Domain, Response> {
    public Domain toDomain(Request request);
    public Response toResponse(Domain domain);
}
