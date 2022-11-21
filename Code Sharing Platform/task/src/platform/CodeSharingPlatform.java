package platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication(scanBasePackages = {"businesslayer", "persistence", "presentation"})
public class CodeSharingPlatform {
    public static void main(String[] args) {
        SpringApplication.run(CodeSharingPlatform.class, args);
    }
}
