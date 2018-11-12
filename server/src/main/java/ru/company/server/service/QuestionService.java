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

}