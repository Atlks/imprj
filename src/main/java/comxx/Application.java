package comxx;

import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
@SpringBootApplication 
///(exclude = ErrorMvcAutoConfiguration.class)
// isuse exclude ,,,page just only 500 error...less than default error detail..
@SpringBootConfiguration
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    //    Tomcat
    }
}