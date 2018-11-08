package ru.company.server;

import org.springframework.web.bind.annotation.*;
import ru.company.shared.*;

import java.util.*;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(value = "/", produces = APPLICATION_JSON_VALUE)
public class HelloResource {

    List<Question> database; // база вопросов
    List<Question> test; // тест

    public HelloResource() {
        database = new ArrayList<>();
    }

    @RequestMapping(value = "/add/question", method = RequestMethod.POST)
    public Boolean getPostHello(@RequestBody QuestionView questionView) { // добавить вопрос
        Question question = new Question();
        question.setId(database.size());
        question.setType(questionView.getType());
        question.setQuestion(questionView.getQuestion());
        question.setAnswer(questionView.getAnswer());
        question.setChoicesAnswer(questionView.getChoicesAnswer());
        database.add(question);
        return true;
    }

    @RequestMapping(value = "/all/question", method = RequestMethod.GET)
    public List<QuestionView> getAllQuestion() { // получить все вопросы
        return questionToQuestionView(database);
    }

    @RequestMapping(value = "/question", method = RequestMethod.POST)
    public Boolean getPostHello(@RequestBody Answer answer) { // принять ответ на вопрос
        for (int i = 0; i < database.size(); i++) {
            if (database.get(i).getId() == answer.getId()) { //нашли вопрос
                if (database.get(i).checkAnswer(answer.getAnswer())) {
                    return true;
                }
                break;
            }
        }
        return false;
    }

    @RequestMapping(value = "/results", method = RequestMethod.GET)
    public List<ResultView> getAllResults() { // получить все результаты
        List<ResultView> results = new ArrayList<>();
        for (int i = 0; i < database.size(); i++) {
            results.add(new ResultView(database.get(i).getId(),
                    database.get(i).getQuestion(), database.get(i).getResult()));
        }
        return results;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public TestView getTest() { // получить тест из двух вопросов
        List<Question> question;
        if (database.size() > 2) {
            Collections.shuffle(database);
            question = database.subList(0, 2);
        } else {
            question = database;
        }
        test = question;
        TestView testView = new TestView(1, questionToQuestionView(question));
        return testView;
    }

    @RequestMapping(value = "/test", method = RequestMethod.POST)
    public TestResultView getAnswerToTest(@RequestBody AnswerTest answerTest) { // принять ответ на тест
        Map<Integer, Boolean> mapAnswer = new HashMap<>();
        Boolean testItogResult = true;
        for (int i = 0; i < test.size(); i++) {
            Question question = test.get(i);
            mapAnswer.put(question.getId(), false); // ответ не правильный изначально
            if (answerTest.getMapAnswer().containsKey(question.getId())) {
                if (question.checkAnswer(answerTest.getMapAnswer().get(question.getId()))) {
                    mapAnswer.put(question.getId(), true);
                } else {
                    testItogResult = false;
                }
            } else {
                testItogResult = false;
            }
        }
        TestResultView testResult = new TestResultView();
        testResult.setId(answerTest.getId());
        testResult.setMapAnswer(mapAnswer);
        testResult.setResult(testItogResult);
        return testResult;
    }

    private List<QuestionView> questionToQuestionView(List<Question> question) {
        List<QuestionView> questionViewList = new ArrayList<>();
        for (int i = 0; i < question.size(); i++) {
            QuestionView questionView = new QuestionView();
            questionView.setType(question.get(i).getType());
            questionView.setId(question.get(i).getId());
            questionView.setQuestion(question.get(i).getQuestion());
            questionView.setChoicesAnswer(question.get(i).getChoicesAnswer());
            questionViewList.add(questionView);
        }
        return questionViewList;
    }
}