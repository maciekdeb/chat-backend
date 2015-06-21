
package chat;

import java.io.File;
import javax.jms.*;
import javax.servlet.Filter;

import chat.filters.FilterLoginBean;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.util.FileSystemUtils;

@SpringBootApplication
@EnableScheduling
@EnableJms
public class Application extends SpringBootServletInitializer {

    private static final String JMS_BROKER_URL = "tcp://localhost:61616";

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Application.class);
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(JMS_BROKER_URL);
    }

    @Bean
    public FilterRegistrationBean someFilterRegistration() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(loginFilter());
        registrationBean.addUrlPatterns("/chat/*");
//        registrationBean.addInitParameter("paramName", "paramValue");
        registrationBean.setName("loginFilter");
        return registrationBean;
    }

    @Bean(name = "loginFilter")
    public Filter loginFilter() {
        return new FilterLoginBean();
    }

    public static void main(String[] args) {
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
    }

}
