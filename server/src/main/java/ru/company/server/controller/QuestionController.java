package ru.company.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

    @RequestMapping(value = "/add/question", method = RequestMethod.POST)
    public Boolean saveQuestion(@RequestBody QuestionView questionView) {
        questionService.saveQuestion(questionView);
        return true;
    }

    @RequestMapping(value = "/all/question", method = RequestMethod.GET)
    public List<QuestionView> getAllQuestion() { // получить все вопросы
        return questionService.getQuestions();
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public Boolean getPostAnswer(@RequestBody AnswerView answer) { // принять ответ на вопрос
        return questionService.checkAnswer(answer);
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
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
}
