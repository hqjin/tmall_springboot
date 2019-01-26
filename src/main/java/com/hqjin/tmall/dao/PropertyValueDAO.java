package com.hqjin.tmall.dao;

import com.hqjin.tmall.pojo.Product;
import com.hqjin.tmall.pojo.Property;
import com.hqjin.tmall.pojo.PropertyValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyValueDAO extends JpaRepository<PropertyValue,Integer>{
    List<PropertyValue> findByProductOrderByIdDesc(Product product);
    PropertyValue getByProductAndProperty(Product product, Property property);
}
