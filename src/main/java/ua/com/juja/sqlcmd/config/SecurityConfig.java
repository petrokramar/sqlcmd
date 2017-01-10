package ua.com.juja.sqlcmd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

//thanks to http://www.mkyong.com/spring-security/spring-security-form-login-using-database/
@Configuration
@EnableWebSecurity
@ComponentScan("ua.com.juja.sqlcmd")
//TODO Change User Schema to Group Schema
//TODO https://hellokoding.com/registration-and-login-example-with-spring-security-spring-boot-spring-data-jpa-hsql-jsp/
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery(
                        "select username,password, enabled from users where username=?")
                .authoritiesByUsernameQuery(
                        "select username, role from user_roles where username=?");
    }

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
//        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
//        auth.inMemoryAuthentication().withUser("dba").password("dba").roles("DBA");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //TODO remove this line
                .csrf().disable()

                .authorizeRequests()
                .antMatchers("/connect").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/databases").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/createdatabase").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/dropdatabase").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/tables").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/createtable").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/cleartable").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/droptable").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/table").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/createrecord").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/deleterecord").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/updaterecord").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
                .antMatchers("/query").access("hasRole('ROLE_ADMIN')")
                .and()
                .formLogin().loginPage("/login").failureUrl("/login?error")
                .usernameParameter("username").passwordParameter("password")
                .and()
                .logout().logoutSuccessUrl("/menu").logoutUrl("/j_spring_security_logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403");
    }
}