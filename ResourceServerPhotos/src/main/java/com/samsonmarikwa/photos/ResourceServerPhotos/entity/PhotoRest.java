package com.samsonmarikwa.photos.ResourceServerPhotos.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PhotoRest {
   private String albumId;
   private String photoId;
   private String userId;
   private String photoTitle;
   private String photoDescription;
   private String photoUrl;
}
