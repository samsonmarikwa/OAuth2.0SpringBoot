package com.samsonmarikwa.albums.ResourceServerAlbums.controllers;

import com.samsonmarikwa.albums.ResourceServerAlbums.entity.AlbumRest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/albums")
public class AlbumsController {
   
   @GetMapping
   public List<AlbumRest> getAlbums() {
      
      AlbumRest al1 = new AlbumRest();
      al1.setAlbumId("albumIdHere");
      al1.setUserId("1");
      al1.setAlbumTitle("Album One title");
      al1.setAlbumDescription("Album 1 description");
      al1.setAlbumUrl("Album 1 URL");
   
      AlbumRest al2 = new AlbumRest();
      al2.setAlbumId("albumIdHere");
      al2.setUserId("2");
      al2.setAlbumTitle("Album Two title");
      al2.setAlbumDescription("Album 2 description");
      al2.setAlbumUrl("Album 2 URL");
   
      AlbumRest al3 = new AlbumRest();
      al3.setAlbumId("albumIdHere");
      al3.setUserId("3");
      al3.setAlbumTitle("Album Three title");
      al3.setAlbumDescription("Album 3 description");
      al3.setAlbumUrl("Album 3 URL");
      
      return Arrays.asList(al1, al2, al3);
   }
}
