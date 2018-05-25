package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContactData;

import static org.hamcrest.MatcherAssert.assertThat;

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
   // app.goTo().gotoHomePage();
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    boolean done = false;

    for (NewContactData contact : contacts) {
      Groups vacantGroups = new Groups(groups);
      vacantGroups.removeAll(contact.getGroups());

      if (!vacantGroups.isEmpty()) {
        GroupData vacantGroup = vacantGroups.iterator().next();
      //  app.contact().toGroup(vacantGroup);

        app.goTo().gotoHomePage();
        app.contact().selectContactById(contact.getId());
        app.contact().selectGroupForAdd(vacantGroup);
        app.contact().addToGroup();
        app.goTo().gotoHomePage();
        done = true;
        break;
      }
    }
    if (!done) {
      app.goTo().groupPage();
      GroupData group = new GroupData().withName("newtest1").withFooter("test3");
      app.group().create(group);
      app.goTo().gotoHomePage();
      NewContactData contact = contacts.iterator().next();

      app.contact().selectContactById(contact.getId());
      app.contact().selectGroupForAdd(group);

      app.contact().addToGroup();

     // contact.inGroup(group);
      app.goTo().gotoHomePage();
      // app.contact().create(contact);
    }

    verifyContactListInUi();


  }

  public void toGroup(GroupData group){

    // app.goTo().gotoHomePage();
    // app.contact().selectContactById(contact.getId());
    //  app.contact().selectGroup(vacantGroup);
    // app.contact().addToGroup();
  }
}


