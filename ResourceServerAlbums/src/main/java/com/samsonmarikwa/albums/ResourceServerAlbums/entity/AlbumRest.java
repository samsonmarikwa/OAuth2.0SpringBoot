package com.samsonmarikwa.albums.ResourceServerAlbums.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AlbumRest {
   
   private String albumId;
   private String userId;
   private String albumTitle;
   private String albumDescription;
   private String albumUrl;
   
}
