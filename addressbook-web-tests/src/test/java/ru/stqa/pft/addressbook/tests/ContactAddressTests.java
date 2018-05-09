package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactAddressTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.contact().all().size() == 0) {
      NewContactData contact = new NewContactData().withFirst_name("1").withLast_name("2").withCompany("3").
              withAddress("Москва, Улица 1 мая, дом 5").withHome("111").withWork("333").
              withEmail2("mama@mail.ru").withEmail3("mama@yandex.ru");
      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }
  }

  @Test
  public void testContactAddress() {

    app.goTo().gotoHomePage();
    NewContactData contact = app.contact().all().iterator().next();
    NewContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAddress(), equalTo(contactInfoFromEditForm.getAddress()));

  }

}
