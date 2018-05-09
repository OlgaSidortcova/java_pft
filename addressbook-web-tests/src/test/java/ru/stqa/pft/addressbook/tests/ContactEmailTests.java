package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class ContactEmailTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().gotoHomePage();

    if (app.contact().all().size() == 0) {
      NewContactData contact = new NewContactData().withFirst_name("1").withLast_name("2").withCompany("3").
              withAddress("Москва, Улица 1 мая, дом 5").withHome("111").withMobile("222").withWork("333").withEmail("mama@rama.ru");
      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }
  }

  @Test
  public void testContactEmail() {

    app.goTo().gotoHomePage();
    NewContactData contact = app.contact().all().iterator().next();
    NewContactData contactInfoFromEditForm = app.contact().infoFromEditForm(contact);

    assertThat(contact.getAllEmails(), equalTo(mergeEmails(contactInfoFromEditForm)));

  }
  private String mergeEmails(NewContactData contact) {

    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().filter((s) -> !s.equals("")).
            map(ContactEmailTests::cleaned).
            collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "");

  }

}
