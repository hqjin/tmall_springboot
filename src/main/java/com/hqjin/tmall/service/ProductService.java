package com.hqjin.tmall.service;

import com.hqjin.tmall.dao.ProductDAO;
import com.hqjin.tmall.pojo.Product;
import com.hqjin.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


@Service
public class ProductService {
    @Autowired
    private ProductDAO productdao;
    @Autowired
    private CategoryService categoryService;
    public void add(Product product){
        productdao.save(product);
    }
    public void delete(int id){
        productdao.deleteById(id);
    }
    public Product get(int id){
        return productdao.getOne(id);
    }
    public void update(Product product){
        productdao.save(product);
    }
    public Page4Navigator<Product> list(int cid,int start,int size,int navigatePage){
        Sort sort=new Sort(Sort.Direction.DESC,"id");
        Pageable pageable=new PageRequest(start,size,sort);
        Page<Product> page= productdao.findByCategory(categoryService.get(cid),pageable);
        //该类主要是对page补充了字段navigatepageNums[]
        return new Page4Navigator<Product>(page,navigatePage);
    }
}
