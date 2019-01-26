package com.hqjin.tmall.web;

import com.hqjin.tmall.pojo.Product;
import com.hqjin.tmall.pojo.PropertyValue;
import com.hqjin.tmall.service.ProductService;
import com.hqjin.tmall.service.PropertyValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PropertyValueController {
    @Autowired
    PropertyValueService propertyValueService;
    @Autowired
    ProductService productService;
    @GetMapping("products/{pid}/propertyValues")
    public List<PropertyValue> list(
            @PathVariable int pid
    ){
        Product product=productService.get(pid);
        propertyValueService.init(product);
        return propertyValueService.list(product);
    }
    @PostMapping("/propertyValues")
    public Object update(
            @RequestBody PropertyValue propertyValue
    ){
        propertyValueService.update(propertyValue);
        return propertyValue;
    }
}
