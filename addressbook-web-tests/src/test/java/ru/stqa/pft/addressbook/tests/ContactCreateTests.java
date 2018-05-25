package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreateTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validContacts() throws IOException {
    String xml = "";
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/contacts.xml")))) {

      String line = reader.readLine();
      while (line != null) {
        xml += line;
        line = reader.readLine();
      }
      XStream xstream = new XStream();
      xstream.processAnnotations(NewContactData.class);
      List<NewContactData> contacts = (List<NewContactData>) xstream.fromXML(xml);
      return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(dataProvider = "validContacts") //(enabled = false)
  public void testNewContact2(NewContactData contact) {
    app.goTo().gotoHomePage();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();

    if (groups.size() != 0) {
      app.goTo().groupPage();

      contact = contact.inGroup(groups.iterator().next());
      app.contact().create(contact);

    } else app.contact().create(contact);

    app.goTo().returnToHomePage();

    assertThat(app.contact().count(), equalTo(before.size() + 1));
    Contacts after = app.db().contacts();

    assertThat(after, equalTo(
            before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    verifyContactListInUi();
  }
}


