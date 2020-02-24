package com.shiyukirito.dto;

import lombok.Data;

import java.security.PrivateKey;
import java.util.ArrayList;
import java.util.List;

@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private List<Integer> pages = new ArrayList<>();
    private Boolean showPrevious; //展示前一页
    private Boolean showNext; //展示后一页
    private  Boolean showFistpage; //展示第一页
    private  Boolean showEndpage; //展示最后一页
    private  Integer page;
    private  Integer totalPage;

    public void sePagination(Integer totalCount, Integer page, Integer size) {
        this.page=page;
        if(totalCount%size == 0){
            this.totalPage = totalCount/size;
        }else {
            this.totalPage = totalCount / size + 1;
        }
        pages.add(page);
        showFistpage = true;
        showEndpage = true;
        for(int i=1;i<=3;i++){
            if(page-i>0){
                pages.add(0,page-i);
                if(page-i==1){
                    showFistpage = false;
                }
            } else{
                showFistpage = false;
            }
            if(page+i<=totalPage) {
                pages.add(page + i);
                if(page+i==totalPage){
                    showEndpage = false;
                }
            } else{
                showEndpage = false;
            }
        }
        if(page == 1){
            showPrevious = false;
        } else{
            showPrevious = true;
        }

        if(page.equals(totalPage)){
            showNext = false;
        } else {
            showNext = true;
        }

    }
}
