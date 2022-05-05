package com.samsonmarikwa.photoappwebclient.response;

import lombok.Data;

@Data
public class AlbumRest {
   
   private String userId;
   private String albumId;
   private String albumTitle;
   private String albumDescription;
   private String albumUrl;
   
}
