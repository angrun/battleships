package conf;

import org.springframework.context.annotation.*;

@Configuration
@Import(DbConfig.class)
@ComponentScan(basePackages = {"controller", "validation"})//Otsi nendest pakketidest
public class MvcConfig {


}