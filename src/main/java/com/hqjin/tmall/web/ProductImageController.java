package com.hqjin.tmall.web;

import com.hqjin.tmall.pojo.Product;
import com.hqjin.tmall.pojo.ProductImage;
import com.hqjin.tmall.service.ProductImageService;
import com.hqjin.tmall.service.ProductService;
import com.hqjin.tmall.util.ImageUtil;
import com.sun.org.apache.bcel.internal.generic.IMUL;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductImageController {
    @Autowired
    private ProductImageService productImageService;
    @Autowired
    private ProductService productService;

    @PostMapping("/productImages")
    public Object add(
            @RequestParam int pid,
            @RequestParam String type,
            MultipartFile image,
            HttpServletRequest request
    ) {
        ProductImage productImage = new ProductImage();
        //新建产品图片类，向其中设置product、type属性，把该实例加入数据库
        //folder字串形成路径，组成是‘img/productSingle/’
        //从request中取得folder的真是上下文地址，形成File
        //在file后加上$pid.jpg形成最终路径File
        //file 的父路径生成
        //把image文件转给file，再用file的尺寸信息生成一个jpg buffer，把file写进这个buffer中
        //把文件尺寸缩小
        Product product = productService.get(pid);
        productImage.setProduct(product);
        productImage.setType(type);
        productImageService.add(productImage);
        String folder = "img/";
        if (ProductImageService.type_single.equals(type)) {
            folder += "productSingle";
        } else {
            folder += "productDetail";
        }
        File imageFolder = new File(request.getServletContext().getRealPath(folder));
        File file = new File(imageFolder, productImage.getId() + ".jpg");
        if (file.getParentFile().exists()) {
        } else file.getParentFile().mkdirs();
        try {
            image.transferTo(file);
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
            e.printStackTrace();
            ;
        }
        String fileName = file.getName();
        if (ProductImageService.type_single.equals(type)) {
            String imageFolder_small = request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle = request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
            f_small.getParentFile().mkdirs();
            f_middle.getParentFile().mkdirs();
            ImageUtil.resizeImage(file, 56, 56, f_small);
            ImageUtil.resizeImage(file, 217, 190, f_middle);
        }
        return productImage;
    }
    @DeleteMapping("/productImages/{id}")
    public String delete(
            @PathVariable int id,
            HttpServletRequest request
    ){
        ProductImage productImage=productImageService.get(id);
        productImageService.delete(id);
        //取得大中小图片路径，删除
        String folder="img/";
        if(productImage.getType().equals(ProductImageService.type_single)){
            folder+="productSingle";
        } else {
            folder += "productDetail";
        }
        File imageFolder=new File(request.getServletContext().getRealPath(folder));
        File file=new File(imageFolder,productImage.getId()+".jpg");
        String fileName=file.getName();
        file.delete();
        if(productImage.getType().equals(ProductImageService.type_single)) {
            String imageFolder_small=request.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle=request.getServletContext().getRealPath("img/productSingle_middle");
            File f_small=new File(imageFolder_small,fileName);
            File f_middle=new File(imageFolder_middle,fileName);
            f_small.delete();
            f_middle.delete();
        }
        return  null;
    }
    @GetMapping("/products/{pid}/productImages")
    public List<ProductImage> list(
            @RequestParam String type,
            @PathVariable int pid
    ){
        Product product=productService.get(pid);
        if(ProductImageService.type_single.equals(type)){
            return productImageService.listSingleProductImages(product);
        }
        else if(ProductImageService.type_detail.equals(type)){
            return productImageService.listDetailProductImages(product);
        }
        else return new ArrayList<>();
    }
}

