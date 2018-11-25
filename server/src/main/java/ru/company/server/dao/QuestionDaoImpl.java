package ru.company.server.dao;

import org.springframework.stereotype.Repository;
import ru.company.server.model.Question;
import ru.company.server.model.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
        List<Integer> questionId = new ArrayList<>();
        if (question.size() > 2) {
            Collections.shuffle(question);
            questionTest = question.subList(0, 2);
        } else {
            questionTest = question;
        }

        for(int i = 0; i< questionTest.size(); i++){
            questionId.add(questionTest.get(i).getId());
        }

        Test test = new Test(questionId);
        em.persist(test);

        return test;
    }

    @Override
    public Test getByIdTest(Integer id) {
        return em.find(Test.class,id);
    }

    @Override
    public List<Test> getTestByNameUser(String user, String role) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Test> criteria = builder.createQuery(Test.class);
        Root<Test> test = criteria.from(Test.class);
        criteria.select(test);
        if(role.equals("ROLE_USER")){
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(test.get("nameUser"), user));
            criteria.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        return em.createQuery(criteria).getResultList();
    }

}
