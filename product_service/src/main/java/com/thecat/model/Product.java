package com.thecat.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table( name = "cs_product.product")
public class Product extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    public int id;

    public String name;
    public String description;
    public String size;
    public String price;
    public LocalDate create_date;
    
    public String toString() {
        StringBuffer sb = new StringBuffer( "Product: \n" );
            
        sb.append("id [ " + this.id + "]\n");
        sb.append("name [ " + this.name + "]\n");
        sb.append("description [ " + this.description + "]\n");
        sb.append("size [ " + this.size + "]\n");
        sb.append("price [ " + this.price + "]\n");
        
        return sb.toString();
    }
}

