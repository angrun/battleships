package dao;

import model.Post;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class PostHsqlDao {


    @PersistenceContext
    public EntityManager em;

    @Transactional //selle meetodi alguses alusta transaktsiooniga - rida paneb midagi andmebaasi, parast teeb commiti
    public Post save(Post post) {

        em.persist(post);

        return post;
    }

    public List<Post> findAll() {

        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    public Post findById(Long id) {

        TypedQuery<Post> query = em.createQuery("select p from Post p where p.id = :id", Post.class);

        query.setParameter("id", id);

        return query.getSingleResult();

    }

    @Transactional
    public void delete(Long id) {

        em.remove(em.find(Post.class, id));
        //em.remove(findById(id);

    }

    @Transactional
    public void delete() {

        Query query = em.createQuery("delete from Post");
        query.executeUpdate();

    }


}
