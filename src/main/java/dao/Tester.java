package dao;

import conf.DbConfig;
import model.Post;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class Tester {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext ctx =
              new AnnotationConfigApplicationContext(DbConfig.class);

        PostDao dao = ctx.getBean(PostDao.class);
//
//        Post post = dao.save(new Post(1L, "order"));
//
//        System.out.println(dao.findAll());
//        System.out.println(dao.findById(1L));
//        dao.delete(2L);






        ctx.close();
    }
}