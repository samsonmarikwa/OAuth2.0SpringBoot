package com.samsonmarikwa.photos.ResourceServerPhotos.controllers;

import com.samsonmarikwa.photos.ResourceServerPhotos.entity.PhotoRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/photos")
public class PhotosController {
   
   @GetMapping
   public List<PhotoRest> getPhotos() {
      
      PhotoRest ph1 = new PhotoRest();
      ph1.setAlbumId("albumIdHere");
      ph1.setPhotoId("1");
      ph1.setUserId("1");
      ph1.setPhotoTitle("Photo 1 title");
      ph1.setPhotoDescription("Photo 1 description");
      ph1.setPhotoUrl("Photo 1 URL");
      
      PhotoRest ph2 = new PhotoRest();
      ph2.setAlbumId("albumIdHere");
      ph2.setPhotoId("2");
      ph2.setUserId("1");
      ph2.setPhotoTitle("Photo 2 title");
      ph2.setPhotoDescription("Photo 2 description");
      ph2.setPhotoUrl("Photo 2 URL");
      
      return Arrays.asList(ph1, ph2);
   
   }
}
