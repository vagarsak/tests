package ru.company.server.dao;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import ru.company.server.model.Question;
import ru.company.server.model.ResultTest;
import ru.company.server.model.Test;
import ru.company.server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;

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
    public List<User> allUser() {
        TypedQuery<User> query = em.createQuery("SELECT p FROM User p", User.class);
        return query.getResultList();
    }

    @Override
    public List<String> getAllQuestionSuccess(String username) {
        Session session = em.unwrap(Session.class);
        String s =  "SELECT question FROM QUESTION question JOIN " +
                "(SELECT KEY, restest.RESULT FROM TEST " +
                "JOIN TEST_RESULTTEST test_res ON TEST.ID = test_res.TEST_ID " +
                "join RESULTTEST restest on test_res.LISTQUESTIONANSWER_ID = restest.ID " +
                "where restest.RESULT  = true and TEST.NAMEUSER = '"+username+"') e on question.ID = e.KEY GROUP BY question";
        SQLQuery q = session.createSQLQuery(s);
        List<String> questions = q.list();
        return questions;
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
        Map<Integer, ResultTest> questionId = new HashMap<>();
        if (question.size() > 2) {
            Collections.shuffle(question);
            questionTest = question.subList(0, 2);
        } else {
            questionTest = question;
        }
        for(int i = 0; i< questionTest.size(); i++){
            ResultTest resultTest = new ResultTest();
            em.persist(resultTest);
            questionId.put(questionTest.get(i).getId(), resultTest);
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
        Root<Test> test_root = criteria.from(Test.class);
        criteria.select(test_root);
        if(role.equals("ROLE_USER")){
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(builder.equal(test_root.get("nameUser"), user));
            criteria.where(predicates.toArray(new Predicate[predicates.size()]));
        }
        List<Test> tests = em.createQuery(criteria).getResultList();
        for(Test test: tests){
            for(Map.Entry<Integer,ResultTest> map : test.getListQuestionAnswer().entrySet() ){
                map.getValue().getAnswer().size();
            }
        }
        return tests;
    }

}
