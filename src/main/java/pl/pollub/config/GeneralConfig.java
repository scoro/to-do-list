package pl.pollub.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import pl.pollub.model.task.Task;

import javax.sql.DataSource;

@Configuration
public class GeneralConfig {

        @Autowired
        @Qualifier("dataSource")
        DataSource dataSource;

        @Bean(name = "sessionFactory")
        public LocalSessionFactoryBean hibernate5SessionFactoryBean() {
            LocalSessionFactoryBean localSessionFactoryBean = new LocalSessionFactoryBean();
            localSessionFactoryBean.setDataSource(dataSource);
            localSessionFactoryBean.setAnnotatedClasses(
                    Task.class
            );

            return localSessionFactoryBean;
        }
}

