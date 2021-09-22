package search.product.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import search.product.model.Photo;
import search.product.model.Product;
import search.product.service.PhotoService;

import java.io.IOException;


@RequestMapping(ApiPath.PHOTO)
@RestController
@RequiredArgsConstructor
public class PhotoController {

    private final PhotoService photoService;

    @ApiOperation(value = "Get Photo By ID", notes ="Returns photo with HTTP.OK. " +
            "Can returns HTTP.BAD_REQUEST if id is null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")}
    )
    @GetMapping(path = "/{id}")
    @ResponseBody
    public ResponseEntity<Photo> getByPhotoId(@PathVariable("id") Long photoId) {
        if(photoId == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Photo photo = photoService.getPhotoById(photoId);
        return new ResponseEntity<>(photo, HttpStatus.OK);
    }

    @ApiOperation(value = "Upload Photo", notes ="Returns photo URL with HTTP.OK. " +
            "Can returns HTTP.BAD_REQUEST if id is null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")}
    )
    @PostMapping(path = "/upload")
    @ResponseBody
    public ResponseEntity<Photo> uploadPhoto(@RequestBody MultipartFile photoFile) {
        if(photoFile != null) {
            try {
                return new ResponseEntity<>(photoService.uploadPhoto(photoFile), HttpStatus.OK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @ApiOperation(value = "Delete Photo By ID", notes ="Returns HTTP.OK. " +
            "Can returns HTTP.BAD_REQUEST if id is null.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 400, message = "Bad request")}
    )
    @DeleteMapping(path = "/delete/{id}")
    @ResponseBody
    public ResponseEntity deletePhoto(@PathVariable("id") Long photoId) {
        if(photoId != null) {
            try {
                photoService.deletePhoto(photoId);
                return new ResponseEntity(HttpStatus.OK);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
