package ru.stqa.pft.addressbook.model;

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
}
