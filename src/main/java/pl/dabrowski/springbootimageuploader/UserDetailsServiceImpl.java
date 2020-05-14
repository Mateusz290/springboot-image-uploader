package pl.dabrowski.springbootimageuploader;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dabrowski.springbootimageuploader.model.AppUser;
import pl.dabrowski.springbootimageuploader.repo.ApiUserRepo;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {       // pośrednik między bazą danych a formularzem logowania    pobiera login z formularza i porownaje zaszyfrowane hasła

    private ApiUserRepo apiUserRepo;

    @Autowired
    public UserDetailsServiceImpl(ApiUserRepo apiUserRepo) {
        this.apiUserRepo = apiUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        return apiUserRepo.findByUsername(s);


    }


}
