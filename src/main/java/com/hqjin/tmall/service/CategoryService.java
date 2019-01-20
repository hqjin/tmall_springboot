package com.hqjin.tmall.service;

import com.hqjin.tmall.dao.CategoryDAO;
import com.hqjin.tmall.pojo.Category;
import com.hqjin.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryDAO categoryDAO;
    public Page4Navigator<Category> list(int start,int size,int navigatePages){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);
        Page pageFromJPA=categoryDAO.findAll(pageable);
        //PageNavigator封装了Page类，并实现了额外功能。
        return new Page4Navigator<>(pageFromJPA,navigatePages);
    }
    public List<Category> list(){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        return categoryDAO.findAll(sort);
    }
    public void add(Category category){
        categoryDAO.save(category);
    }
    public void delete(Integer id){
        categoryDAO.deleteById(id);
    }
}
