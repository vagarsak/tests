package ru.company.server.service;

import ru.company.shared.*;

import java.util.List;

public interface QuestionService {

    /**
     * Получить список Question
     *
     * @return List
     */
    List<QuestionView> getQuestions();

    /**
     * Получить список User
     *
     * @return List
     */
    List<UserView> getAllUser();

    /**
     * Получить список правильно отвечанных вопросов
     *
     * @param username
     * @return List
     */
    List<String> getAllQuestionSuccess(String username);

    /**
     * Проверить ответ Question
     *
     * @param answer
     * @return Boolean
     */
    Boolean checkAnswer(AnswerView answer);

    /**
     * Добавить новый Question
     *
     * @param questionView
     */
    void saveQuestion(QuestionView questionView);

    /**
     * Получить список результатов Questions
     *
     * @return List
     */
    List<ResultView> getAllResults();

    /**
     * Получить Test
     *
     * @return Test
     */
    TestView getTest();

    /**
     * Проверить ответ на Test
     *
     * @param answerTest
     * @return TestResultView
     */
    TestResultView getTestAnswer(AnswerTestView answerTest);

    /**
     * Получить список результатов Tests
     *
     * @param user, role
     * @return List
     */
    List<TestResultsView> getAllResultsTests(String user, String role);

}