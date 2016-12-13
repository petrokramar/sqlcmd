package ua.com.juja.sqlcmd.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password("user").roles("USER");
        auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
        auth.inMemoryAuthentication().withUser("dba").password("dba").roles("DBA");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                //TODO remove this line
                .csrf().disable()

                .authorizeRequests()
//                .antMatchers("/dba/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_DBA')")
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
                .formLogin()
//                .and()
//                .formLogin().loginPage("/login").failureUrl("/login?error")
//                .usernameParameter("username").passwordParameter("password")
//                .and()
//                .logout().logoutSuccessUrl("/login?logout")
                .and()
                .exceptionHandling().accessDeniedPage("/403");

    }
}

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import ua.com.juja.sqlcmd.service.UserDetailsServiceImpl;
//
//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true)
//@ComponentScan("ua.com.juja.sqlcmd")
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    @Autowired
//    private UserDetailsServiceImpl userDetailsService;
//
//    // регистрируем нашу реализацию UserDetailsService
//    // а также PasswordEncoder для приведения пароля в формат SHA1
//    @Autowired
//    public void registerGlobalAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsService).passwordEncoder(getShaPasswordEncoder());
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // включаем защиту от CSRF атак
//        http.csrf()
//                .disable()
//                // указываем правила запросов
//                // по которым будет определятся доступ к ресурсам и остальным данным
//                .authorizeRequests()
//                .antMatchers("/resources/**", "/**").permitAll()
//                .anyRequest().permitAll()
//                .and();
//
//        http.formLogin()
//                // указываем страницу с формой логина
//                .loginPage("/login")
//                // указываем action с формы логина
//                .loginProcessingUrl("/j_spring_security_check")
//                // указываем URL при неудачном логине
//                .failureUrl("/login?error")
//                // Указываем параметры логина и пароля с формы логина
//                .usernameParameter("j_username")
//                .passwordParameter("j_password")
//                // даем доступ к форме логина всем
//                .permitAll();
//
//        http.logout()
//                // разрешаем делать логаут всем
//                .permitAll()
//                // указываем URL логаута
//                .logoutUrl("/logout")
//                // указываем URL при удачном логауте
//                .logoutSuccessUrl("/login?logout")
//                // делаем не валидной текущую сессию
//                .invalidateHttpSession(true);
//
//    }
//
//    // Указываем Spring контейнеру, что надо инициализировать <b></b>ShaPasswordEncoder
//    // Это можно вынести в WebAppConfig, но для понимаемости оставил тут
//    @Bean
//    public ShaPasswordEncoder getShaPasswordEncoder(){
//        return new ShaPasswordEncoder();
//    }
//
//}
