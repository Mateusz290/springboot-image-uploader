package pl.dabrowski.springbootimageuploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import pl.dabrowski.springbootimageuploader.model.AppUser;
import pl.dabrowski.springbootimageuploader.repo.ApiUserRepo;

import java.util.Collections;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {   // konfiguracja - dostęp dla użytkownikó      defniowanie na jaki endpoint może wejść user


    private UserDetailsServiceImpl userDetailsService;
    private ApiUserRepo appUserRepo;

    @Autowired
    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService, ApiUserRepo appUserRepo) {
        this.userDetailsService = userDetailsService;
        this.appUserRepo = appUserRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication().withUser(new User("Jan", passwordEncoder().encode("Jan123"), Collections.singleton(new SimpleGrantedAuthority("user "))));   // tworzenie użytkownika w pamięci

        auth.userDetailsService(userDetailsService);   // powiązanie bazy danych z autentyfikacja i autoryzacja

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/test1").hasRole("USER")   //authenticated()         dostęp po zalogowaniu
                .antMatchers("/test2").hasRole("ADMIN")
                .and()
                .formLogin().permitAll();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){// passwordEncoder pozwala na zdefiniowanie rodzaju szyfrowania i zdeszyfrowania użytkowników. Zamienia hasło na szyfrowany obiekt
        return new BCryptPasswordEncoder();
    }


    @EventListener(ApplicationReadyEvent.class)
    public void get() {

        AppUser appUserUser = new AppUser("UserJan",passwordEncoder().encode("Nowak"), "ROLE_USER");
            AppUser appUserAdmin= new AppUser("AdminJan",passwordEncoder().encode("Nowak"), "ROLE_ADMIN");
        appUserRepo.save(appUserUser);
        appUserRepo.save(appUserAdmin);


    }

}
