package com.hqjin.tmall.service;

import com.hqjin.tmall.dao.ProductDAO;
import com.hqjin.tmall.dao.PropertyValueDAO;
import com.hqjin.tmall.pojo.Product;
import com.hqjin.tmall.pojo.Property;
import com.hqjin.tmall.pojo.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//通过初始化，自动增加默认值：产品得分类，分类得属性，若属性值为空，则设置默认值
@Service
public class PropertyValueService {
    @Autowired
    PropertyValueDAO propertyValueDAO;
    @Autowired
    PropertyService propertyService;
    public void init(Product product){
        List<Property> properties=propertyService.listByCategory(product.getCategory());
        for(Property property:properties){
            PropertyValue propertyValue=getByPropertyAndProduct(property,product);
            if(propertyValue==null){
                propertyValue=new PropertyValue();
                propertyValue.setProperty(property);
                propertyValue.setProduct(product);
                propertyValueDAO.save(propertyValue);
            }
        }
    }
    public PropertyValue getByPropertyAndProduct(Property property,Product product){
        return propertyValueDAO.getByProductAndProperty(product,property);
    }
    public List<PropertyValue> list(Product product){
        return propertyValueDAO.findByProductOrderByIdDesc(product);
    }
    public void update(PropertyValue value){
        propertyValueDAO.save(value);
    }
}
