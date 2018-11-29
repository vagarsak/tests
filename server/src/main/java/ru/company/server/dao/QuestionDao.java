package ru.company.server.dao;

import ru.company.server.model.Question;
import ru.company.server.model.Test;
import ru.company.server.model.User;

import java.util.List;

public interface QuestionDao {

    /**
     * Получить все объекты Question
     *
     * @return List
     */
    List<Question> all();

    /**
     * Получить все объекты User
     *
     * @return List
     */
    List<User> allUser();

    /**
     * Получить все Question правильно отвечанных
     *
     * @param username
     * @return List
     */
    List<String> getAllQuestionSuccess(String username);

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

    /**
     * Получить все объекты Test
     *
     * @param user, role
     * @return List
     */
    List<Test> getTestByNameUser(String user, String role);

}