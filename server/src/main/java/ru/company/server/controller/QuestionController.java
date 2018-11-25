package ru.company.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.company.server.service.QuestionService;
import ru.company.shared.*;


import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class QuestionController {

    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @RequestMapping(value = "/admin/add/question", method = RequestMethod.POST)
    public Boolean saveQuestion(@RequestBody QuestionView questionView) {
        questionService.saveQuestion(questionView);
        return true;
    }

    @RequestMapping(value = "/admin/all/question", method = RequestMethod.GET)
    public List<QuestionView> getAllQuestion() { // получить все вопросы
        return questionService.getQuestions();
    }

    @RequestMapping(value = "/admin/question", method = RequestMethod.POST)
    public Boolean getPostAnswer(@RequestBody AnswerView answer) { // принять ответ на вопрос
        return questionService.checkAnswer(answer);
    }

    @RequestMapping(value = "/admin/question/results", method = RequestMethod.GET)
    public List<ResultView> getAllResults() { // получить все результаты
        return questionService.getAllResults();
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public TestView getTest() { // получить тест
        return questionService.getTest();
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public TestResultView checkAnswerToTest(@RequestBody AnswerTestView answerTest) {
        return questionService.getTestAnswer(answerTest);
    }

    @RequestMapping(value = "/results/tests", method = RequestMethod.GET)
    public List<TestResultsView> getAllResultsTests() {
        String user = SecurityContextHolder.getContext().getAuthentication().getName();
        String role = ((List)SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities()).get(0).toString();
        return questionService.getAllResultsTests(user,role);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public UserView getUser() {
        UserView userView = new UserView();
        userView.setName(SecurityContextHolder.getContext().getAuthentication().getName());
        Integer size = SecurityContextHolder.getContext().getAuthentication().getAuthorities().size();
        List<String> role = new ArrayList<>();
        for(int i = 0; i<size;i++){
            role.add(((List)SecurityContextHolder.getContext().getAuthentication()
                    .getAuthorities()).get(i).toString());
        }
        userView.setAuthorities(role);
        return userView;
    }

}
