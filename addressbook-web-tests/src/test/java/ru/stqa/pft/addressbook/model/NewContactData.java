package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

import java.io.File;
import java.util.Objects;

@XStreamAlias("contact")
public class NewContactData {
  private String first_name;
  private String last_name;
  private String company;
  private String address;
  private String home;
  private String mobile;
  private String work;
  private String fax;
  private String email;
  private String email2;
  private String email3;
  private String gruop;

  @XStreamOmitField
  private int id = Integer.MAX_VALUE;
  private String allphones;
  private String allemails;
  private File photo;

  public String getFirst_name() {
    return first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public String getCompany() {
    return company;
  }

  public String getAddress() {
    return address;
  }

  public String getHome() {
    return home;
  }

  public String getMobile() {
    return mobile;
  }

  public String getWork() {
    return work;
  }

  public String getFax() {
    return fax;
  }

  public String getEmail() {
    return email;
  }

  public String getEmail2() {
    return email2;
  }

  public String getEmail3() {
    return email3;
  }


  public String getGruop() {
    return gruop;
  }

  public int getId() {
    return id;
  }

  public String getAllPhones() {
    return allphones;
  }

  public String getAllEmails() {
    return allemails;
  }

  public File getPhoto() {
    return photo;
  }

  public NewContactData withId(int id) {

    this.id = id;
    return this;
  }

  public NewContactData withFirst_name(String first_name) {
    this.first_name = first_name;
    return this;
  }

  public NewContactData withLast_name(String last_name) {
    this.last_name = last_name;
    return this;
  }

  public NewContactData withCompany(String company) {
    this.company = company;
    return this;
  }

  public NewContactData withAddress(String address) {
    this.address = address;
    return this;
  }

  public NewContactData withHome(String home) {
    this.home = home;
    return this;
  }

  public NewContactData withMobile(String mobile) {
    this.mobile = mobile;
    return this;
  }

  public NewContactData withWork(String work) {
    this.work = work;
    return this;
  }

  public NewContactData withFax(String fax) {
    this.fax = fax;
    return this;
  }

  public NewContactData withEmail(String email) {
    this.email = email;
    return this;
  }

  public NewContactData withEmail2(String email2) {
    this.email2 = email2;
    return this;
  }

  public NewContactData withEmail3(String email3) {
    this.email3 = email3;
    return this;
  }

  public NewContactData withGruop(String gruop) {
    this.gruop = gruop;
    return this;
  }

  public NewContactData withAllPhones(String allphones) {

    this.allphones = allphones;
    return this;
  }

  public NewContactData withAllEmails(String allemails) {

    this.allemails = allemails;
    return this;
  }

  public NewContactData withPhoto(File photo){

    this.photo = photo;
    return this;
  }

  @Override
  public String toString() {
    return "NewContactData{" +
            "first_name='" + first_name + '\'' +
            ", last_name='" + last_name + '\'' +
            ", id=" + id +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    NewContactData that = (NewContactData) o;
    return id == that.id &&
            Objects.equals(first_name, that.first_name) &&
            Objects.equals(last_name, that.last_name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(first_name, last_name, id);
  }


}
