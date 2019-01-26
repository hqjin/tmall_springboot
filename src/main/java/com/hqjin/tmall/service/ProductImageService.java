package com.hqjin.tmall.service;

import com.hqjin.tmall.dao.ProductImageDAO;
import com.hqjin.tmall.pojo.Product;
import com.hqjin.tmall.pojo.ProductImage;
import org.hsqldb.lib.tar.PIFData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ProductImageService {
    @Autowired
    private ProductImageDAO productImageDAO;
    @Autowired
    private ProductService productService;
    public static final String type_single="single";
    public static final String type_detail="detail";

    public void add(ProductImage productImage){
        productImageDAO.save(productImage);
    }
    public void delete(int id){
        productImageDAO.deleteById(id);
    }
    public ProductImage get(int id){
        return productImageDAO.getOne(id);
    }
    public List<ProductImage> listSingleProductImages(Product product){
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product,type_single);
    }
    public List<ProductImage> listDetailProductImages(Product product){
        return productImageDAO.findByProductAndTypeOrderByIdDesc(product,type_detail);
    }
    public void setFirstProductImages(Product product){
        List<ProductImage>singleImages=listSingleProductImages(product);
        if(!singleImages.isEmpty()){
            product.setFirstProductImage(singleImages.get(0));
        }
        else{
            product.setFirstProductImage(new ProductImage());
        }
    }
    public void setFirstProductImages(List<Product> products){
        for(Product product:products){
            setFirstProductImages(product);
        }
    }
}
