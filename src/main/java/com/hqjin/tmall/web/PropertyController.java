package com.hqjin.tmall.web;

import com.hqjin.tmall.pojo.Category;
import com.hqjin.tmall.pojo.Property;
import com.hqjin.tmall.service.PropertyService;
import com.hqjin.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PropertyController {
    @Autowired
    private PropertyService propertyService;
    @GetMapping("/categories/{cid}/properties")
    public Page4Navigator<Property> list(
            @PathVariable int cid,
            @RequestParam(value="start",defaultValue = "0")int start,
            @RequestParam(value="size",defaultValue = "5")int size
    ){
        start=start<0?0:start;
        Page4Navigator<Property> page=propertyService.list(cid,start,size,5);
        return page;
    }
    @GetMapping("/properties/{id}")
    public Property get(@PathVariable int id){
        return propertyService.get(id);
    }
    @PostMapping("/properties")
    //犯错：post的参数放在body里，所以应@RequestBody
    public Object add(@RequestBody Property bean){
        propertyService.add(bean);
        return  bean;
    }
    @DeleteMapping("/properties/{id}")
    public  String delete(@PathVariable int id){
        propertyService.delete(id);
        return null;
    }
    @PutMapping("/properties")
    public Object update(@RequestBody Property property){
        propertyService.update(property);
        return property;
    }
}
