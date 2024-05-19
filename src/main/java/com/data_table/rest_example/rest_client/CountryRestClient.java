package com.data_table.rest_example.rest_client;

import com.data_table.rest_example.dto.CountryFindListResponseDto;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "url-rest-service-v1")
public interface CountryRestClient {

    @GET
    @Path("/country/list")
    @Produces(MediaType.APPLICATION_JSON)
    CountryFindListResponseDto findList(@QueryParam("filterName") String filterName,
                                        @QueryParam("filterCode") String filterCode,
                                        @QueryParam("sortField") String sortField,
                                        @QueryParam("sortOrder") String sortOrder,
                                        @QueryParam("pageIndex") int pageIndex,
                                        @QueryParam("pageSize") int pageSize);

    @GET
    @Path("/country/list")
    @Produces(MediaType.APPLICATION_JSON)
    CountryFindListResponseDto findAll(@QueryParam("pageIndex") int pageIndex,
                                       @QueryParam("pageSize") int pageSize);

}
