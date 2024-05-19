package com.data_table.rest_example.rest_controller;

import com.data_table.rest_example.rest_service.CountryRestService;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

@Path("/country")
@RequestScoped
public class CountryRestController {

    final CountryRestService countryRestService;

    @Inject
    public CountryRestController(CountryRestService countryRestService) {
        this.countryRestService = countryRestService;
    }

    @GET
    @Path("/list")
    @Produces("application/json; charset=utf-8")
    public Response getFindList(
            @QueryParam("filterCode") @DefaultValue("") String filterCode,
            @QueryParam("filterName") @DefaultValue("") String filterName,
            @QueryParam("sortField") @DefaultValue("code") String sortField,
            @QueryParam("sortOrder") @DefaultValue("asc") String sortOrder,
            @QueryParam("pageIndex") @DefaultValue("0") int pageIndex,
            @QueryParam("pageSize") @DefaultValue("10") int pageSize) {
        return Response.status(Response.Status.OK)
                .entity(countryRestService.getFindList(filterCode, filterName, sortField, sortOrder, pageIndex, pageSize))
                .build();
    }
}
