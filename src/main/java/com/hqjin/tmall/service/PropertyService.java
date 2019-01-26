package com.hqjin.tmall.service;

import com.hqjin.tmall.dao.PropertyDAO;
import com.hqjin.tmall.pojo.Category;
import com.hqjin.tmall.pojo.Property;
import com.hqjin.tmall.util.Page4Navigator;
import com.sun.org.apache.bcel.internal.generic.CASTORE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyService {
    @Autowired
    private PropertyDAO propertyDAO;
    @Autowired
    private CategoryService categoryService;
    public void add(Property property){
        propertyDAO.save(property);
    }
    public void delete(int id){
        propertyDAO.deleteById(id);
    }
    public Property get(int id){
        return propertyDAO.getOne(id);
    }
    public Page4Navigator<Property> list(int cid, int start, int size, int navigatePages){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);
        Category category=categoryService.get(cid);
        Page<Property> page=propertyDAO.findByCategory(category,pageable);
        return new Page4Navigator<>(page,navigatePages);
    }
    public void update(Property property){
        propertyDAO.save(property);
    }
    public List<Property> listByCategory(Category category){
        return propertyDAO.findByCategory(category);
    }
}
