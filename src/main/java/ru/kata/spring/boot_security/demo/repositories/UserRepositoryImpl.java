package ru.kata.spring.boot_security.demo.repositories;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<User> findAll() {
        return em.createQuery("select distinct u from User u left join fetch u.roles").getResultList();
    }

    @Override
    public void save(User user) {
        em.persist(user);
    }


    @Override
    public User findById(long id) {
        if (em.find(User.class, id) == null) {
            throw new NullPointerException("User with id=" + id + " is not find");
        }
        return em.find(User.class, id);
    }

    @Override
    public void updateUser(long id, User updatedUser) {
        User user = findById(id);
        if (user != null) {
            user.setFirstName(updatedUser.getFirstName());
            user.setLastName(updatedUser.getLastName());
            user.setAge(updatedUser.getAge());
            user.setEmail(updatedUser.getEmail());
            user.getRoles().clear();
            user.getRoles().addAll(updatedUser.getRoles());
            em.merge(user);
        }
    }

    @Override
    public void deleteById(long id) {
        em.remove(findById(id));
    }

    @Override
    public User findByEmail(String username) {
        String hql = "Select u from User u left join fetch u.roles where u.email=:emailParam";
        em.createQuery(hql).setParameter("emailParam", username).getSingleResult();
        return (User) em.createQuery(hql).setParameter("emailParam", username).getSingleResult();
    }
}
