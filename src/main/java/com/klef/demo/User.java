package com.klef.demo;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table
public class User {
@Id
public String username;
@Column
public String firstname;
@Column
public String lastname;
@Column
public String country;
@Column
public String city;
@Column
public String phone;
@Column
public String email;
@Column
public String password;
public String getUsername() {
  return username;
}
public void setUsername(String username) {
  this.username = username;
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
public String getCountry() {
  return country;
}
public void setCountry(String country) {
  this.country = country;
}
public String getCity() {
  return city;
}
public void setCity(String city) {
  this.city = city;
}
public String getPhone() {
  return phone;
}
public void setPhone(String phone) {
  this.phone = phone;
}
public String getEmail() {
  return email;
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

}