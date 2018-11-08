package dao;

import conf.DbConfig;

import model.Post;
import model.PostRow;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext ctx =
              new AnnotationConfigApplicationContext(DbConfig.class);

        PostHsqlDao dao = ctx.getBean(PostHsqlDao.class);
        Post post = new Post();
        List s = new ArrayList();
        s.add(new PostRow("ss", 12, 12));
        post.setOrderRows(s);
        dao.save(post);
        System.out.println(dao.findAll());
        System.out.println(dao.findById(1L));


        ctx.close();
    }
}