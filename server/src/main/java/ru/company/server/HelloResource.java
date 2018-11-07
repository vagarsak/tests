package ru.company.server;

import org.springframework.web.bind.annotation.*;
import ru.company.shared.*;

import java.util.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class HelloResource {

    List<Question> database; // база вопросов
    List<Result> results;   // результаты
    Test test; // тест
    public HelloResource() {
        database = new ArrayList<>();
        results = new ArrayList<>();
    }

    @RequestMapping(value = "/add/question", method = RequestMethod.POST)
    public String getPostHello(@RequestBody Question question) { // добавить вопрос
        question.setId(database.size());
        database.add(question);
        results.add(new Result(question.getId()));
        return "1";
    }

    @RequestMapping(value = "/add/question", method = RequestMethod.GET)
    public List<Question> getAllQuestion() { // получить все вопросы
        return database;
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public String getPostHello(@RequestBody Answer answer) { // принять ответ на тест
        for(int i =0; i < database.size();i++){
            if (database.get(i).getId() ==  answer.getId() ){ //нашли тест
                Integer rez = database.get(i).checkAnswer(answer.getAnswer());
                switch (rez){
                    case (1):
                        results.get(answer.getId()).addSuccessful();
                        return "1";
                    case (0):
                        results.get(answer.getId()).addQuantity();
                }
                break;
            }
        }
        return "0";
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public List<Result> getAllResults() { // получить все результаты
        return results;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Test getTest() { // получить тест из двух вопросов
        List<Question> question;
        if(database.size() > 2){
            Collections.shuffle(database);
            question = database.subList(0, 2);
        }else{
            question = database;
        }
        test = new Test();
        test.setId(1);
        test.setListQuestion(question.toArray(new Question[question.size()]));
        return test;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public TestResult getAnswerToTest(@RequestBody AnswerTest answerTest) { // получить тест
        Map<Integer,Integer> mapAnswer = new HashMap<>();
        for (Map.Entry<Integer, String[]> entry : answerTest.getMapAnswer().entrySet()) {
            mapAnswer.put(entry.getKey(),0); // ответ не правильный изначально
            for(int i =0; i < database.size();i++){
                if (database.get(i).getId() ==  entry.getKey() ){ //нашли вопрос
                    Integer rez = database.get(i).checkAnswer(entry.getValue());
                    switch (rez){
                        case (1):
                            results.get(entry.getKey()).addSuccessful();
                            mapAnswer.put(entry.getKey(),1);
                            break;
                        case (0):
                            results.get(entry.getKey()).addQuantity();
                    }
                    break;
                }
            }
        }
        TestResult testResult = new TestResult();
        testResult.setId(answerTest.getId());
        testResult.setMapAnswer(mapAnswer);
        return testResult;
    }
}