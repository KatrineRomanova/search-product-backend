package search.product.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import search.product.model.Photo;
import search.product.repo.PhotoRepository;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.Map;


@Service
@AllArgsConstructor
public class PhotoService {
    public final PhotoRepository photoRepository;

    public Photo getPhotoById(Long id) {
        if (id != null)
            return photoRepository.findById(id).orElse(null);
        return null;
    }

    private Photo savePhoto(Photo photo){
        return photoRepository.save(photo);
    }

    public Photo uploadPhoto(MultipartFile multipartFile) throws IOException {
        URL photoUrl = uploadPhotoToCloudinary(multipartFile);

        Photo photo = new Photo(photoUrl.toString());
        return savePhoto(photo);
    }

    public URL uploadPhotoToCloudinary(MultipartFile multipartFile) throws IOException {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator(10, new SecureRandom());

        String fileName = randomStringGenerator.nextString();

        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dil2e8syz",
                "api_key", "472111977347574",
                "api_secret", "_y6fAMQBfc68zKCjq7fJc-Fqeeg"));

        File toUpload = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(toUpload);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.flush();

        Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
        toUpload.delete();

        String urlString = uploadResult.get("url").toString();
        URL url = new URL(urlString);
        return url;
    }
}
