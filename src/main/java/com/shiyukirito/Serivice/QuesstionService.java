package com.shiyukirito.Serivice;

import com.shiyukirito.dto.PaginationDTO;
import com.shiyukirito.dto.QuestionDTO;
import com.shiyukirito.mapper.QuestionMapper;
import com.shiyukirito.mapper.UserMapper;
import com.shiyukirito.model.Question;
import com.shiyukirito.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//当一个请求需要俩个以上的Mapper组合使用时，我们使用Service
@Service
public class QuesstionService {
    @Autowired
    QuestionMapper questionMapper;

    @Autowired
    UserMapper userMapper;

    public PaginationDTO list(Integer page, Integer size) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        Integer totalCount = questionMapper.count();
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //避免出现page过大，内容做出了改善，但是分页功能没有改善。
        //而提前判断page又需要totalPage的值，所以将totalPage的值提前拿出去判断，把totalCount拿出去单独判断来改善分页功能。
        if(totalCount%size == 0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount / size + 1;
        }
        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        if(page==0){
            return paginationDTO;
        }
        paginationDTO.sePagination(totalCount,page,size);
        Integer offset = (page-1)*size;
        List<Question> questions = questionMapper.list(offset,size);
        for(Question question : questions){
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(int userId, Integer page, Integer size) {
        List<QuestionDTO> questionDTOList = new ArrayList<>();
        Integer totalCount = questionMapper.countByuserId(userId);
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        //避免出现page过大，内容做出了改善，但是分页功能没有改善。
        //而提前判断page又需要totalPage的值，所以将totalPage的值提前拿出去判断，把totalCount拿出去单独判断来改善分页功能。
        if(totalCount%size == 0){
            totalPage = totalCount/size;
        }else {
            totalPage = totalCount / size + 1;
        }
        if(page<1){
            page = 1;
        }
        if(page>totalPage){
            page=totalPage;
        }
        //这里需要加一下page为0的判断，page为什么会为0，因为如果用户一个帖子没发，在page=totalPage;page就会为0。
        if(page==0){
            return paginationDTO;
        }
        paginationDTO.sePagination(totalCount,page,size);
        Integer offset = (page-1)*size;
        List<Question> questions = questionMapper.listByUserId(userId,offset,size);
        for(Question question : questions){
            User user = userMapper.findByID(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }
}
