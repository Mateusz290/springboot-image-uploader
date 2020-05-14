package pl.dabrowski.springbootimageuploader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.dabrowski.springbootimageuploader.model.AppUser;

@Repository
public interface ApiUserRepo extends JpaRepository<AppUser, Long> {

    AppUser findByUsername(String username);

}
