package search.product.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import search.product.model.Photo;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Map;


@Service
@AllArgsConstructor
public class PhotoCloudinaryService {

    private final Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
            "cloud_name", "dil2e8syz",
            "api_key", "472111977347574",
            "api_secret", "_y6fAMQBfc68zKCjq7fJc-Fqeeg"));


    /**
     * Upload photo file to Cloudinary
     * and fill in cloudinaryId and url on new photo object from the uploaded file
     *
     * @param multipartFile - input file for uploading
     * @return new photo object with filled cloudinaryId and url
     * @throws IOException
     */
    public Photo uploadPhotoToCloudinary(MultipartFile multipartFile) throws IOException {
        RandomStringGenerator randomStringGenerator = new RandomStringGenerator(10, new SecureRandom());
        String fileName = randomStringGenerator.nextString();

        File toUpload = new File(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(toUpload);
        fileOutputStream.write(multipartFile.getBytes());
        fileOutputStream.flush();

        Map uploadResult = cloudinary.uploader().upload(toUpload, ObjectUtils.emptyMap());
        toUpload.delete();

        return new Photo(uploadResult.get("public_id").toString(),
                         uploadResult.get("url").toString());
    }

    /**
     * Delete photo from Cloudinary by input cloudinaryId
     *
     * @param cloudinaryId
     * @return true if photo deleted from Cloudinary, else return false
     * @throws IOException
     */
    public Boolean deletePhotoFromCloudinary(String cloudinaryId) throws IOException {
        Map uploadResult = cloudinary.uploader().destroy(cloudinaryId, ObjectUtils.emptyMap());
        return "ok".equals(
                uploadResult.get("result").toString());
    }
}
