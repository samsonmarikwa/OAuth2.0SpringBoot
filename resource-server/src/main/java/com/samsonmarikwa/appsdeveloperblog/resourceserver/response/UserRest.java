package com.samsonmarikwa.appsdeveloperblog.resourceserver.response;

public class UserRest {
   private String firstname;
   private String lastname;
   private String userId;
   
   public UserRest(String firstname, String lastname, String userId) {
      this.firstname = firstname;
      this.lastname = lastname;
      this.userId = userId;
   }
   
   public String getFirstname() {
      return firstname;
   }
   
   public void setFirstname(String firstname) {
      this.firstname = firstname;
   }
   
   public String getLastname() {
      return lastname;
   }
   
   public void setLastname(String lastname) {
      this.lastname = lastname;
   }
   
   public String getUserId() {
      return userId;
   }
   
   public void setUserId(String userId) {
      this.userId = userId;
   }
}
