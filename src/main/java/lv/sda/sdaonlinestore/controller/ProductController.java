package lv.sda.sdaonlinestore.controller;

import lv.sda.sdaonlinestore.entity.Order;
import lv.sda.sdaonlinestore.entity.Product;
import lv.sda.sdaonlinestore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    private List<Product> displayProducts(){
        List<Product> allProducts = productService.getAllProducts();
        return allProducts;
    }

    @GetMapping("/byId")
    public List<Product> displayProductsById(@RequestParam Long productId){
        Product productById = productService.findById(productId);
        List<Product> lst = new ArrayList<>();
        lst.add(productById);
        return lst;
    }

    @PostMapping
    public List<Product> addProduct(@RequestBody Product product){
        productService.saveOrUpdate(product);
        List<Product> lst = new ArrayList<>();
        lst.add(product);
        return lst;
    }

    @PutMapping
    public List<Product> updateProductUpdate(@RequestBody Product product){
        productService.update(product, product.getProductId());
        List<Product> lst = new ArrayList<>();
        lst.add(product);
        return lst;
    }

    @DeleteMapping
    public String deleteProduct(@RequestBody List<Product> product){
        product.forEach(p -> {
            productService.delete(p);
        });
        return "Product deleted";
    }

}
