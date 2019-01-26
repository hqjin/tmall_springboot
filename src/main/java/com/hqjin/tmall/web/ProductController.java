package com.hqjin.tmall.web;

import com.hqjin.tmall.pojo.Product;
import com.hqjin.tmall.service.CategoryService;
import com.hqjin.tmall.service.ProductImageService;
import com.hqjin.tmall.service.ProductService;
import com.hqjin.tmall.util.Page4Navigator;
import javafx.beans.binding.ObjectExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.PrimitiveIterator;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductImageService productImageService;
    @PostMapping("/products")
    public Object add(@RequestBody Product product){
        product.setCreateDate(new Date());
        productService.add(product);
        return product;
    }
    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable int id){
        productService.delete(id);
        return null;
    }
    @GetMapping("/products/{id}")
    public Product get(@PathVariable int id){
        return productService.get(id);
    }
    @GetMapping("/categories/{cid}/products")
    public Page4Navigator<Product> list(
            @PathVariable int cid,
            @RequestParam(value="start",defaultValue = "0") int start,
            @RequestParam(value="size",defaultValue = "5")int size
    ){
        start=start<0?0:start;
        Page4Navigator<Product> page= productService.list(cid,start,size,5);
        productImageService.setFirstProductImages(page.getContent());
        return page;
    }
    @PutMapping("/products")
    public Object update(@RequestBody Product product){
        productService.update(product);
        return product;
    }
}
