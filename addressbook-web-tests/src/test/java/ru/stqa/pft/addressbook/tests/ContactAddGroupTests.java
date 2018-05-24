package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
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

public class ContactAddGroupTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHomePage();
      NewContactData contact = new NewContactData().
              withFirst_name("1FirstName1").withLast_name("2LastName2").withCompany("Company3").withAddress("Address");
      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("test3"));
      app.goTo().gotoHomePage();
    }
  }

  @Test //(enabled = false)
  public void testContactAddGroup() {

    app.goTo().gotoHomePage();
    Contacts before = app.db().contacts();
    Groups groups = app.db().groups();
    NewContactData contact = before.iterator().next();
    GroupData group = groups.iterator().next();

    contact = contact.inGroup(groups.iterator().next());



  //  NewContactData contact = new NewContactData().withFirst_name("bgvbgf").withLast_name("свмавм").
     //       inGroup(group.iterator().next());



   // if (group.size() != 0) {
   //   app.goTo().groupPage();
   //   GroupData selectGroup = group.iterator().next();
      //contact = contact.withGruop(selectGroup.getName());
   // }

   // NewContactData contact = new NewContactData().withFirst_name("bgvbgf").withLast_name("свмавм");
// app.contact().create(contact);
 //   app.goTo().returnToHomePage();

   // assertThat(app.contact().count(), equalTo(before.size() + 1));
  //  Contacts after = app.db().contacts();

  //  assertThat(after, equalTo(
   //         before.withAdded(contact.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
    veryfaiContactListInUi();



  }
}


