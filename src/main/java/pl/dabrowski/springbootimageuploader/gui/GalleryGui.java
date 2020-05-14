package pl.dabrowski.springbootimageuploader.gui;

import java.util.List;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import pl.dabrowski.springbootimageuploader.model.Image;
import pl.dabrowski.springbootimageuploader.repo.ImageRepo;

@Route("gallery")
public class GalleryGui extends VerticalLayout {

    private ImageRepo imageRepo;


    @Autowired
    public GalleryGui(ImageRepo imageUpader) {
        this.imageRepo = imageUpader;
        List<Image> all = imageUpader.findAll();
        all.stream().forEach(element -> {
            com.vaadin.flow.component.html.Image image =
            new com.vaadin.flow.component.html.Image(element.getImageAddress(), "brak");
            add(image);
        } );
    }

}
