package com.hqjin.tmall.util;

import org.springframework.data.domain.Page;

import java.util.List;

public class Page4Navigator<T> {
    private Page<T> pageFromJPA;
    private int navigatePages;
    private int totalPages;
    private int number;
    private long totalElements;
    private int size;
    private int numberOfElements;
    private List<T> content;
    private boolean isHasContent;

    private boolean first;

    private boolean last;

    private boolean isHasNext;

    private boolean isHasPrevious;

    private int[] navigatepageNums;

    public Page4Navigator(){
        //为了redis从json转换为Page4Navigator而专门准备的。
    }
    public Page4Navigator(Page<T> pageFromJPA,int navigatePages){
        this.pageFromJPA=pageFromJPA;
        //指定显示的页数
        this.navigatePages=navigatePages;
        totalPages=pageFromJPA.getTotalPages();
        number=pageFromJPA.getNumber();
        totalElements=pageFromJPA.getTotalElements();
        size=pageFromJPA.getSize();
        numberOfElements=pageFromJPA.getNumberOfElements();
        content=pageFromJPA.getContent();
        isHasContent=pageFromJPA.hasContent();
        first=pageFromJPA.isFirst();
        last=pageFromJPA.isLast();
        isHasNext=pageFromJPA.hasNext();
        isHasPrevious=pageFromJPA.hasPrevious();
        calcNavigatepageNums();
    }
    private void calcNavigatepageNums(){
        int[] navigatepageNums;
        int totalPages=getTotalPages();
        int num=getNumber();
        //当总页数小于等于导航页码数
        if(totalPages<=navigatePages){
            navigatepageNums=new int[totalPages];
            for(int i=0;i<totalPages;i++){
                navigatepageNums[i]=i+1;
            }
        }
        else{//当总页数大于导航页码数时
            navigatepageNums=new int[navigatePages];
            int startNum=num-navigatePages/2;
            int endNum=num+navigatePages/2;
            if(startNum<1){
                startNum=1;
                for(int i=0;i<navigatePages;i++){
                    navigatepageNums[i]=startNum++;
                }
            }
            else if(endNum>totalPages){
                endNum=totalPages;
                for(int i=navigatePages-1;i>=0;i--){
                    navigatepageNums[i]=endNum--;
                }
            }
            else{
                for(int i=0;i<navigatePages;i++){
                    navigatepageNums[i]=startNum++;
                }
            }
        }
        this.navigatepageNums=navigatepageNums;
    }

    public Page<T> getPageFromJPA() {
        return pageFromJPA;
    }

    public void setPageFromJPA(Page<T> pageFromJPA) {
        this.pageFromJPA = pageFromJPA;
    }

    public int getNavigatePages() {
        return navigatePages;
    }

    public void setNavigatePages(int navigatePages) {
        this.navigatePages = navigatePages;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }

    public boolean isHasContent() {
        return isHasContent;
    }

    public void setHasContent(boolean hasContent) {
        isHasContent = hasContent;
    }

    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    public boolean isLast() {
        return last;
    }

    public void setLast(boolean last) {
        this.last = last;
    }

    public boolean isHasNext() {
        return isHasNext;
    }

    public void setHasNext(boolean hasNext) {
        isHasNext = hasNext;
    }

    public boolean isHasPrevious() {
        return isHasPrevious;
    }

    public void setHasPrevious(boolean hasPrevious) {
        isHasPrevious = hasPrevious;
    }

    public int[] getNavigatepageNums() {
        return navigatepageNums;
    }

    public void setNavigatepageNums(int[] navigatepageNums) {
        this.navigatepageNums = navigatepageNums;
    }
}
