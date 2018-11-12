package ru.company.server.dao;

import ru.company.server.model.Question;
import ru.company.server.model.Test;

import java.util.List;

public interface QuestionDao {

    /**
     * Получить все объекты Question
     *
     * @return List
     */
    List<Question> all();

    /**
     * Сохранить Question
     *
     * @param question
     * @return
     */
    void saveQuestion(Question question);

    /**
     * Получить Question по ID
     *
     * @param id
     * @return Question
     */
    Question getById(Integer id);

    /**
     * Получить Test
     *
     * @return Test
     */
    Test getTest();

    /**
     * Получить Test по ID
     *
     * @param id
     * @return Test
     */
    Test getByIdTest(Integer id);

}