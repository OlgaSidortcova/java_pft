package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class ContactPhoneTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.contact().all().size() == 0) {
      NewContactData contact = new NewContactData().withFirst_name("1").withLast_name("2").withCompany("3").
              withAddress("Горький, Улица 1 мая, дом 65").withHome("111").withMobile("222").withEmail("mama@rama.ru");
      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }
  }

  @Test
  public void testContactPhones() {

    app.goTo().gotoHomePage();
    NewContactData contact = app.contact().all().iterator().next();
    NewContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllPhones(), equalTo(mergePhones(contactInfoFromEditForm)));
  }

  private String mergePhones(NewContactData contact) {

    return Arrays.asList(contact.getHome(), contact.getMobile(), contact.getWork()).stream().filter((s) -> !s.equals("")).
            map(ContactPhoneTests::cleaned).
            collect(Collectors.joining("\n"));
  }

  public static String cleaned(String phone) {
    return phone.replaceAll("\\s", "").replaceAll("[-()]", "");
  }
}
