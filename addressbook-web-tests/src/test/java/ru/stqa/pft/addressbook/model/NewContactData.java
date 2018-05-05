package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class NewContactData {
  private final String first_name;
  private final String last_name;
  private final String company;
  private final String address;
  private final String home;
  private final String mobile;
  private final String work;
  private final String fax;
  private final String email;
  private String gruop;
  private int id;



  public NewContactData(String first_name, String last_name, String company, String address, String home, String mobile, String work, String fax, String email, String gruop, int id) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.fax = fax;
    this.email = email;
    this.gruop = gruop;
    this.id = id;
  }


  public NewContactData(String first_name, String last_name, String company, String address, String home, String mobile, String work, String fax, String email, String gruop) {
    this.first_name = first_name;
    this.last_name = last_name;
    this.company = company;
    this.address = address;
    this.home = home;
    this.mobile = mobile;
    this.work = work;
    this.fax = fax;
    this.email = email;
    this.gruop = gruop;

    this.id = Integer.MAX_VALUE;
  }

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

  public String getFax() {    return fax;  }

  public String getEmail() {
    return email;
  }
  public String getGruop() {
    return gruop;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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
    return Objects.equals(first_name, that.first_name) &&
            Objects.equals(last_name, that.last_name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(first_name, last_name);
  }

}
