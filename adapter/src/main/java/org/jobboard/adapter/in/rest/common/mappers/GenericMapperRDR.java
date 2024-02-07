package org.jobboard.adapter.in.rest.common.mappers;

public interface GenericMapperRDR<Request, Domain, Response> {
    public Domain toDomain(Request request);
    public Response toResponse(Domain domain);
}
