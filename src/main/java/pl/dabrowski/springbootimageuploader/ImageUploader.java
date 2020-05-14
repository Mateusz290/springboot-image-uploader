package pl.dabrowski.springbootimageuploader;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import pl.dabrowski.springbootimageuploader.repo.ImageRepo;

import java.io.File;
import java.io.IOException;
import java.util.Map;


@Service
public class ImageUploader {

    private Cloudinary cloudinary;
    private ImageRepo imageRepo;

    public ImageUploader(ImageRepo imageRepo, @Value("${cloudNameValue}") String cloudNameValue,  @Value("${apiKeyValue}") String apiKeyValue, @Value("${apiSecretValue}") String apiSecretValue) {

        this.imageRepo=imageRepo;

        cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", cloudNameValue,
                "api_key", apiKeyValue,
                "api_secret", apiSecretValue));

    }


    public String uploadFileAndSaveToDb(String patch) {
        File file = new File(patch);
        Map uploadResult = null;
        try {

             uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());

        } catch (IOException exc) {

        }

        return uploadResult.get("url").toString();

    }



}
