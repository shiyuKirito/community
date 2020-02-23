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
        paginationDTO.sePagination(totalCount,page,size);
        if(page<1){
            page = 1;
        }
        if(page>paginationDTO.getTotalPage()){
            page=paginationDTO.getTotalPage();
        }
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
}
