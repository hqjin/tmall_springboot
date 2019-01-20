package com.hqjin.tmall.web;

import com.hqjin.tmall.pojo.Category;
import com.hqjin.tmall.service.CategoryService;
import com.hqjin.tmall.util.ImageUtil;
import com.hqjin.tmall.util.Page4Navigator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @GetMapping(value="/categories")
    public Page4Navigator<Category> list(
            @RequestParam (value="start", defaultValue="0")int start,
            @RequestParam(value="size",defaultValue = "5")int size
    ) throws  Exception{
        start=start<0?0:start;
        Page4Navigator<Category> page= categoryService.list(start,size,5);
        return page;
    }
    @PostMapping(value = "/categories")
    //此处的变量名image是前端定义好的，不能被修改
    //但是bean的名字可以被改成category，可以正常识别？
    public Object add(Category category, HttpServletRequest request, MultipartFile image)  throws IOException
    {
        categoryService.add(category);
        saveOrUpdateImageFile(category,  request,image);
        return category;
    }
    public void saveOrUpdateImageFile(Category category, HttpServletRequest request, MultipartFile image)
            throws IOException{
        File imageFolder=new File(request.getServletContext().getRealPath("img/category"));
        File file=new File(imageFolder,category.getId()+".jpg");
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();
        }
        image.transferTo(file);
        BufferedImage bufferedImage= ImageUtil.change2jpg(file);
        ImageIO.write(bufferedImage,"jpg",file);
    }
}
