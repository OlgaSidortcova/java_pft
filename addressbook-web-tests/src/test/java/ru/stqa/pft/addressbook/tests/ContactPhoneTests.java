package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.contact().list().size() == 0) {
          NewContactData contact = new NewContactData().withFirst_name("1").withLast_name("2").withCompany("3").
              withAddress("4").withHome("111").withMobile("222").withWork("333");
      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }
  }

  @Test
  public  void testContactPhones(){

    app.goTo().gotoHomePage();
    NewContactData contact = app.contact().all().iterator().next();
    NewContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getHome(), equalTo(cleaned(contactInfoFromEditForm.getHome())));
    assertThat(contact.getMobile(), equalTo(cleaned(contactInfoFromEditForm.getMobile())));
    assertThat(contact.getWork(), equalTo(cleaned(contactInfoFromEditForm.getWork())));
  }

  public String cleaned (String phone){
return phone.replaceAll("\\s","").replaceAll("[-()]","");

  }
}
