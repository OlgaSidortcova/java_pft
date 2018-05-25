package ru.stqa.pft.addressbook.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamOmitField;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.File;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "addressbook")
@XStreamAlias("contact")
public class NewContactData {
  @Column(name = "firstname")
  private String first_name;

  @Column(name = "lastname")
  private String last_name;

  @Column(name = "company")
  private String company;

  @Column(name = "address")
  @Type(type = "text")
  private String address;

  @Column(name = "home")
  @Type(type = "text")
  private String home;

  @Column(name = "mobile")
  @Type(type = "text")
  private String mobile;

  @Column(name = "work")
  @Type(type = "text")
  private String work;

  @Column(name = "fax")
  @Type(type = "text")
  private String fax;

  @Column(name = "email")
  @Type(type = "text")
  private String email;

  @Column(name = "email2")
  @Type(type = "text")
  private String email2;

  @Column(name = "email3")
  @Type(type = "text")
  private String email3;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "address_in_groups",
          joinColumns = @JoinColumn(name = "id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
  private Set<GroupData> groups = new HashSet<GroupData>();

  @Id
  @Column(name = "id")
  @XStreamOmitField
  private int id = Integer.MAX_VALUE;

  @Transient
  private String allphones;

  @Transient
  private String allemails;

  @Column(name = "photo")
  @Type(type = "text")
  private String photo;

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

  public Groups getGroups() {
    return new Groups(groups);
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
    if (photo == null) {
      return null;
    } else {

      return new File(photo);
    }
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

  public NewContactData withAllPhones(String allphones) {

    this.allphones = allphones;
    return this;
  }

  public NewContactData withAllEmails(String allemails) {

    this.allemails = allemails;
    return this;
  }

  public NewContactData withPhoto(File photo) {

    this.photo = photo.getPath();
    return this;
  }

  @Override
  public String toString() {
    return "NewContactData{" +
            "first_name='" + first_name + '\'' +
            ", last_name='" + last_name + '\'' +
            ", gruops='" + groups + '\'' +
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
            Objects.equals(last_name, that.last_name) &&
            Objects.equals(company, that.company) &&
            Objects.equals(address, that.address) &&
            Objects.equals(home, that.home) &&
            Objects.equals(mobile, that.mobile) &&
            Objects.equals(work, that.work) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(email, that.email) &&
            Objects.equals(email2, that.email2) &&
            Objects.equals(email3, that.email3);
  }

  @Override
  public int hashCode() {
    return Objects.hash(first_name, last_name, company, address, home, mobile, work, fax, email, email2, email3, id);
  }

  public NewContactData inGroup(GroupData group) {
    groups.add(group);
    return this;
  }
  public NewContactData outGroup(GroupData group) {
    groups.remove(group);
    return this;
  }


private Object readResolve(){
    groups = new HashSet<GroupData>();
    return this;
}
}
