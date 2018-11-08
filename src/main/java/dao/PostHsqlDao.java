package dao;

import model.Post;
import model.PostRow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.swing.text.html.parser.Entity;
import javax.transaction.Transactional;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PostHsqlDao {

    @Autowired
    private JdbcTemplate template;

    @PersistenceContext
    public EntityManager em;

    @Transactional //selle meetodi alguses alusta transaktsiooniga - rida paneb midagi andmebaasi, parast teeb commiti
    public Post save(Post post) {

        em.persist(post);

        return post;
    }

    public List<Post> findAll() {
        //TODO

        return em.createQuery("select p from Post p", Post.class).getResultList();
    }

    public Post findById(Long id) {

        TypedQuery<Post> query = em.createQuery("select p from Post p where p.id = :id", Post.class);

        query.setParameter("id", id);

        return query.getSingleResult();

    }
//
    public void delete(Long id) {
        //DONE

        TypedQuery<Post> query =
                em.createQuery("delete p from Post p", Post.class);

    }


}
