package ru.company.server.dao;

import org.springframework.stereotype.Repository;
import ru.company.server.model.Question;
import ru.company.server.model.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;

@Repository
public class QuestionDaoImpl implements QuestionDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Question> all() {
        TypedQuery<Question> query = em.createQuery("SELECT p FROM Question p", Question.class);
        return query.getResultList();
    }

    @Override
    public void saveQuestion(Question question) {
        em.persist(question);
    }

    @Override
    public Question getById(Integer id) {
        return em.find(Question.class,id);
    }

    @Override
    public Test getTest() {
        List<Question> question = all();
        List<Question> questionTest;
        if (question.size() > 2) {
            Collections.shuffle(question);
            questionTest = question.subList(0, 2);
        } else {
            questionTest = question;
        }

        Test test = em.find(Test.class,1);
        if(test != null){
            test.setListQuestion(questionTest);
            em.merge(test);
        }
        else {
            test = new Test(questionTest);
            em.persist(test);
        }
        return test;
    }

    @Override
    public Test getByIdTest(Integer id) {
        return em.find(Test.class,id);
    }

}
