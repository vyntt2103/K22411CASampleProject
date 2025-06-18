package com.models;

import com.example.k22411casampleproject.R;

import java.util.ArrayList;

public class ListProduct {
    ArrayList<Product> products;
    public ListProduct() {
        products=new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products){
        this.products=products;
    }


    public void generate_sample_products(){
        Product p1 = new Product(1, "Coca Cola", 100, 10.0, R.mipmap.ic_coca);
        Product p2 = new Product(2, "Pepsi", 120, 9.5, R.mipmap.ic_pepsi);
        Product p3 = new Product(3, "7Up", 90, 8.0, R.mipmap.ic_sevenup);
        Product p4 = new Product(4, "Fanta", 85, 8.5, R.mipmap.ic_fanta);
        Product p5 = new Product(5, "Sprite", 95, 9.0, R.mipmap.ic_sprite);
        products.add(p1);
        products.add(p2);
        products.add(p3);
        products.add(p4);
        products.add(p5);
    }

}
