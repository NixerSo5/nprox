package com.nixer.nprox;

import com.nixer.nprox.jwt.JwtAuthenticationTokenFilter;
import com.nixer.nprox.thread.GetHonnyPoolDay;
import com.nixer.nprox.thread.GetHonnyPoolState;
import com.nixer.nprox.tools.SpringContextHolder;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.nixer.nprox.dao")
@SpringBootApplication
@EnableSwagger2
@EnableAsync
public class NproxApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(NproxApplication.class, args);
        GetHonnyPoolState honnyPoolStateThread = SpringContextHolder.getBean(GetHonnyPoolState.class);
        GetHonnyPoolDay honnyPoolDay = SpringContextHolder.getBean(GetHonnyPoolDay.class);
        honnyPoolStateThread.HonnyPoolStateThreadRun();
        honnyPoolDay.HonnyPoolDayThreadRun();
    }


    @Bean
    public FilterRegistrationBean registration(JwtAuthenticationTokenFilter filter) {
        FilterRegistrationBean registration = new FilterRegistrationBean(filter);
        registration.setEnabled(false);
        return registration;
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SpringContextHolder springContextHolder() {
        return new SpringContextHolder();
    }
}
