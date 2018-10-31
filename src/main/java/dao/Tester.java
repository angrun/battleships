package dao;

import conf.DbConfig;
import model.Post;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Tester {

    public static void main(String[] args) throws Exception {

        ConfigurableApplicationContext ctx =
              new AnnotationConfigApplicationContext(DbConfig.class);

        PostDao dao = ctx.getBean(PostDao.class);

        ctx.close();
    }
}