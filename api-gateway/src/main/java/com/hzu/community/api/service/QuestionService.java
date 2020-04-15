package com.hzu.community.api.service;


import com.hzu.community.api.dao.QuestionDao;
import com.hzu.community.api.dao.UserDao;
import com.hzu.community.api.model.*;


import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class QuestionService {
    @Autowired
    private QuestionDao questionDao;
    @Autowired
    private UserDao userDao;
    public PaginationDTO getQuestions(String search, String tag, Integer page, Integer size, String sort) {
//        如果search有值，先字符串处理
        if (StringUtils.isNotBlank(search)) {
            String[] tags = StringUtils.split(search, " ");//以空格分隔
            search = Arrays
                    .stream(tags)
                    .collect(Collectors.joining("|"));
        }

        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount=questionDao.count(search,tag);
        paginationDTO.setPagination(totalCount,page,size);
        Integer offset = size * (page - 1);
        List<Question> questions =  questionDao.getQuestions(offset,size,search,tag,sort);
        List<QuestionDTO>questionDTOList=new ArrayList<>();

        for (Question question : questions){
            User  user = userDao.ById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//把第一个的所有属性拷贝到第二个
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);





        return paginationDTO;
    }

    public QuestionDTO getById(Integer id) {
        QuestionDTO questionDTO = questionDao.getById(id);
        User user = userDao.getUser(questionDTO.getCreator());
        questionDTO.setUser(user);
        return questionDTO;

    }

    public void createOrUpdate(Question question) {
        if (question.getId()==null){
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionDao.addQuestion(question);
        }else {
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            questionDao.update(question);
        }

    }

    /**
     * 相关问题
     * @param queryDTO
     * @return
     */
    public List<QuestionDTO> selectRelated(QuestionDTO queryDTO) {
        String[] tags=StringUtils.split(queryDTO.getTag(),",");//通过逗号隔开
        String regexpTag = Arrays.stream(tags).collect(Collectors.joining("|"));//通过|拼接
        Question question = new Question();
        question.setId(queryDTO.getId());
        question.setTag(regexpTag);
        List<Question>questions=questionDao.selectRelated(question);
        List<QuestionDTO> questionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(q,questionDTO);
            return questionDTO;
        }).collect(Collectors.toList());
        return questionDTOS;
    }

    public PaginationDTO selectMyQuestion(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalCount=questionDao.countByUserId(userId);
        paginationDTO.setPagination(totalCount,page,size);
        Integer offset = size * (page - 1);
        List<Question> questions = questionDao.selectMyQuestion(userId,offset,size);
        List<QuestionDTO>questionDTOList=new ArrayList<>();
        for (Question question : questions){
            User  user = userDao.ById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);//把第一个的所有属性拷贝到第二个
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setData(questionDTOList);


        return paginationDTO;
    }

    public void incView(Integer id) {

        Question question = questionDao.getQuestion(id);//把question里面id等于id的数据传过来
        Question updateQuestion = new Question();//新建一个方法
        updateQuestion.setId(question.getId());//把question里的id赋给updateQuestion
//        updateQuestion.setViewCount(question.getViewCount()+1);//把question里的浏览数加1赋给updateQuestion
        questionDao.updateViewCount(updateQuestion);//用updateViewCount方法更新数据库
    }

    public void deleteById(Question question) {
        questionDao.deleteById(question);
    }
}






