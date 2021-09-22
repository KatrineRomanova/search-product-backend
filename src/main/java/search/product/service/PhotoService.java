package search.product.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import search.product.model.Photo;
import search.product.repo.PhotoRepository;

import java.io.IOException;


@Service
@AllArgsConstructor
public class PhotoService {
    public final PhotoRepository photoRepository;
    public final PhotoCloudinaryService photoCloudinaryService;

    public Photo getPhotoById(Long id) {
        if (id != null)
            return photoRepository.findById(id).orElse(null);
        return null;
    }

    private Photo savePhoto(Photo photo) {
        if (photo != null)
            return photoRepository.save(photo);
        return null;
    }

    @Transactional
    public Photo uploadPhoto(MultipartFile multipartFile) throws IOException {
        Photo photo = photoCloudinaryService.uploadPhotoToCloudinary(multipartFile);
        return savePhoto(photo);
    }

    @Transactional
    public void deletePhoto(Long id) throws IOException {
        if (id != null) {
            Photo photo = getPhotoById(id);
            if(photoCloudinaryService.deletePhotoFromCloudinary(photo.getCloudinaryId()))
                photoRepository.deleteById(id);
        }
    }
}
