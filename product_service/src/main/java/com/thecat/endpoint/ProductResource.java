package com.thecat.endpoint;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.thecat.services.ProductService;
import com.thecat.model.Product;

@Path("/product")
public class ProductResource {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Product> listProduct() {
        return productService.listProduct();
    }

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

    @GET
    @Path( "health")
    @Produces(MediaType.TEXT_PLAIN)
    public String health() {
        return "SUCCESS";
    }
    
}