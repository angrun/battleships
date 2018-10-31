package conf;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@Import(DbConfig.class)
@ComponentScan(basePackages = {"controller", "validation"})//Otsi nendest pakketidest
public class MvcConfig {


}