package ru.company.server.dao;

import org.springframework.stereotype.Repository;
import ru.company.server.model.User;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDetailsDaoImp implements UserDetailsDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public User findUserByUsername(String username) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = builder.createQuery(User.class);
        Root<User> user = criteria.from(User.class);
        criteria.select(user);
        List<Predicate> predicates = new ArrayList<>();
        predicates.add(builder.equal(user.get("username"), username));
        criteria.where(predicates.toArray(new Predicate[predicates.size()]));
        try{
            return em.createQuery(criteria).getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
    }
}