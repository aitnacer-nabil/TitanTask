/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.titans.entities;

import org.titans.util.Utils;

import javax.lang.model.type.UnionType;

/**
 *
 * @author hp
 */
public class User {
   private String id ;
   private String username;
   private String email;
   private String password;
   Role role;

   @Override
   public String toString() {
      return "User{" +
              "id='" + id + '\'' +
              ", username='" + username + '\'' +
              ", email='" + email + '\'' +
              ", password='" + password + '\'' +
              ", role=" + role +
              '}';
   }

   public User(String id, String username, String email, String password, Role role) {
      this.id = id;
      this.username = username;
      this.email = email;
      this.password = password;
      this.role = role;
   }

   public User(String username, String email, String password, Role role) {
      this.id = Utils.GenerateId();
      this.username = username;
      this.email = email;
      this.password = password;
      this.role = role;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getEmail() {
      return email;
   }

   public String getId() {
      return id;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Role getRole() {
      return role;
   }

   public void setRole(Role role) {
      this.role = role;
   }
}
