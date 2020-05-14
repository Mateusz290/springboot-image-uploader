package pl.dabrowski.springbootimageuploader.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dabrowski.springbootimageuploader.model.Image;

public interface ImageRepo extends JpaRepository<Image, Long> {
}
