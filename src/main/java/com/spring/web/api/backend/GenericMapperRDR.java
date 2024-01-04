package com.spring.web.api.backend;

public interface GenericMapperRDR<Request, Domain, Response> {
    public Domain toDomain(Request request);
    public Response toResponse(Domain domain);
}
