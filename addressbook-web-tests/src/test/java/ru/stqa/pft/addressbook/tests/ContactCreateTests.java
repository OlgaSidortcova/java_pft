package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.io.File;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreateTests extends TestBase {

  @Test //(enabled = false)
  public void testNewContact2() {

    app.goTo().gotoHomePage();
    Contacts before = app.contact().all();
    File photo = new File("src/test/resources/tyri.jpg");
    NewContactData contact = new NewContactData().withFirst_name("1").withLast_name("2").withCompany("3").
            withAddress("4").withHome("111").withMobile("222").withWork("333").withPhoto(photo).withEmail("mama@ramyyy.com");

    app.contact().create(contact);
    app.goTo().returnToHomePage();

    assertThat(app.contact().count(), equalTo(before.size() + 1));

    app.goTo().gotoHomePage();
    Contacts after = app.contact().all();
    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testCurrentDir() {

    File correntDir = new File(".");
    correntDir.getAbsolutePath();
    File photo = new File("src/test/resources/tyri.jpg");
    System.out.println(correntDir.getAbsolutePath());
    System.out.println(photo.getAbsolutePath());
    System.out.println(photo.exists());
  }

}


