package com.thecat.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

import com.thecat.services.ProductService;
import com.thecat.model.Product;

@Path("/product")
public class ProductResource {

    @Inject
    ProductService productService;

    @Operation(summary = "Return all the products list")
    @APIResponses( value = {
        @APIResponse( responseCode = "200",description = "Products are returned" )
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProduct() {
        return productService.listProduct();
    }

    @Operation(summary = "Select a particular product")
    @APIResponses( value = {
        @APIResponse( responseCode = "200",description = "Select product is return" ),
        @APIResponse(responseCode = "204",description = "No product found")
    })
    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Product getAProduct(@PathParam( "id" ) Integer id) {
        return productService.findProductById(id);
    }

    @GET
    @Path("size/{size}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> getAProduct(@PathParam( "size" ) String size) {
        return productService.findProductBySize(size);
    }

    @GET
    @Path("count")
    @Produces(MediaType.APPLICATION_JSON)
    public long getCount() {
        return productService.getProductCount();
    }

    @Operation(summary = "Check if the service is up and ready to receive request")
    @APIResponses( value = {
        @APIResponse( responseCode = "200",description = "Service is Ready" )
    })
    @GET
    @Path( "health")
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "SUCCESS";
    }
    
}