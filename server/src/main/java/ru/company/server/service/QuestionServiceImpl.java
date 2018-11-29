package ru.company.server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.company.server.dao.QuestionDao;
import ru.company.server.model.*;
import ru.company.shared.*;

import javax.persistence.Index;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService {

    private final QuestionDao dao;

    @Autowired
    public QuestionServiceImpl(QuestionDao dao) {
        this.dao = dao;
    }

    @Override
    public List<QuestionView> getQuestions() {
        List<Question> questions = dao.all();
        return questionToQuestionView(questions);
    }

    @Override
    public List<UserView> getAllUser() {
        List<User> users = dao.allUser();
        return userToUserView(users);
    }

    @Override
    public List<String> getAllQuestionSuccess(String username) {
        return dao.getAllQuestionSuccess(username);
    }

    @Override
    public Boolean checkAnswer(AnswerView answer) {
        Question question = dao.getById(answer.getId());
         if (question != null){
             return question.checkAnswer(answer.getAnswer());
         }
        return false;
    }

    @Override
    public void saveQuestion(QuestionView questionView) {
        Question question = new Question();
        question.setType(questionView.getType());
        question.setQuestion(questionView.getQuestion());
        question.setAnswer(questionView.getAnswer());
        question.setChoicesAnswer(questionView.getChoicesAnswer());
        question.setResult(new Result());
        dao.saveQuestion(question);
    }

    @Override
    public List<ResultView> getAllResults() {
        List<Question> questions = dao.all();
        List<ResultView> results = new ArrayList<>();
        for(Question question : questions){
            results.add(new ResultView(question.getId(),
                    question.getQuestion(), question.getResultMap()));
        }
        return results;
    }

    @Override
    public TestView getTest() {
        Test test = dao.getTest();
        return testToTestView(test);
    }

    @Override
    public TestResultView getTestAnswer(AnswerTestView answerTest) {
        Test test = dao.getByIdTest(answerTest.getId());
        List<Question> listQuestion = idToListQuestion(test.getListQuestionAnswer());
        Map<Integer, Boolean> mapAnswer = new HashMap<>();
        test.setResult(true);
        for (int i = 0; i < listQuestion.size(); i++) {
            Question question = listQuestion.get(i);
            Integer questionId = question.getId();
            mapAnswer.put(questionId, false);
            if (answerTest.getMapAnswer().containsKey(questionId)) {
                if (question.checkAnswer(answerTest.getMapAnswer().get(questionId))) {
                    mapAnswer.put(questionId, true);
                    test.getListQuestionAnswer().get(questionId).setResult(true);
                } else {
                    test.setResult(false);
                    test.getListQuestionAnswer().get(questionId).setResult(false);
                }
                test.getListQuestionAnswer().get(questionId).setAnswer(answerTest.getMapAnswer().get(questionId));

            } else {
                test.setResult(false);
            }
        }
        return testResultToTestResultView(test,mapAnswer);
    }

    @Override
    public List<TestResultsView> getAllResultsTests(String user, String role) {
        return testToTestResultsView(dao.getTestByNameUser(user, role));
    }

    private List<TestResultsView> testToTestResultsView(List<Test> tests) {
        List<TestResultsView> testResultsViewList = new ArrayList<>();
        for(Test test :tests){
            TestResultsView testResultsView = new TestResultsView();
            testResultsView.setId(test.getId());
            testResultsView.setResult(test.getResult());
            testResultsView.setNameUser(test.getNameUser());
            Map<String, List<String>> mapQuestionAnswer = new HashMap<>();
            for(Question question :idToListQuestion(test.getListQuestionAnswer())){
                mapQuestionAnswer.put(question.getQuestion()
                        ,test.getListQuestionAnswer().get(question.getId()).getAnswer());
            }
            testResultsView.setQuestionAnswer(mapQuestionAnswer);
            testResultsViewList.add(testResultsView);
        }
        return testResultsViewList;
    }


    private TestView testToTestView(Test test) {
        TestView testView = new TestView();
        testView.setId(test.getId());
        testView.setListQuestion(questionToQuestionView(idToListQuestion(test.getListQuestionAnswer())));
        return testView;
    }

    private TestResultView testResultToTestResultView(Test test,Map<Integer, Boolean> mapAnswer) {
        TestResultView testResult = new TestResultView();
        testResult.setId(test.getId());
        testResult.setMapAnswer(mapAnswer);
        testResult.setResult(test.getResult());
        return testResult;
    }

    private List<QuestionView> questionToQuestionView(List<Question> questions) {
        List<QuestionView> questionViewList = new ArrayList<>();
        for (Question question : questions) {
            QuestionView questionView = new QuestionView();
            questionView.setType(question.getType());
            questionView.setId(question.getId());
            questionView.setQuestion(question.getQuestion());
            question.getChoicesAnswer().size();
            questionView.setChoicesAnswer(question.getChoicesAnswer());
            questionViewList.add(questionView);
        }
        return questionViewList;
    }

    private List<Question> idToListQuestion(Map<Integer,ResultTest> listQuestionAnswer){
        List<Question> listQuestion = new ArrayList<>();
        for(Integer i : listQuestionAnswer.keySet()){
            listQuestion.add(dao.getById(i));
        }
        return listQuestion;
    }

    private List<UserView> userToUserView(List<User> users){
        List<UserView> userViews = new ArrayList<>();
        for(User user : users){
            UserView userView = new UserView();
            userView.setName(user.getUsername());
            userViews.add(userView);
        }
        return userViews;
    }

}
