package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContactData;

import static ru.stqa.pft.addressbook.tests.TestBase.app;

public class ContactDeleteGroupTests extends TestBase{

  @BeforeMethod
  public void ensurePreconditions() {
    boolean gr = false;
    GroupData group = new GroupData();
    int a= 0;

    if (app.db().groups().size() == 0) {
      app.goTo().groupPage();
      group.withName("test1").withFooter("test3");
      app.group().create(group);
      gr = true;
      app.goTo().gotoHomePage();
    }

    if (app.db().contacts().size() == 0) {
      app.goTo().gotoHomePage();
      NewContactData contact = new NewContactData().
              withFirst_name("1FirstName1").withLast_name("2LastName2").withCompany("Company3").withAddress("Address");
      if (gr) {
        contact.inGroup(group);
      }

      app.contact().create(contact);
      app.goTo().returnToHomePage();
    }
  }

  @Test //(enabled = false)
  public void testContactDeleteGroup() {
    // app.goTo().gotoHomePage();
    Contacts contacts = app.db().contacts();
    Groups groups = app.db().groups();
    boolean done = false;

    for (NewContactData contact : contacts) {
      Groups vacantGroups = contact.getGroups();
      if (!vacantGroups.isEmpty()) {
        GroupData vacantGroup = vacantGroups.iterator().next();
        app.goTo().gotoHomePage();
        app.contact().selectGroupForDelete(vacantGroup);
        app.contact().selectContactById(contact.getId());

        // app.contact().selectGroup(vacantGroup);
        app.contact().dellFromGroup();
        app.goTo().gotoHomePage();
        done = true;
        break;
      }
    }
    if (!done) {
      NewContactData contact = contacts.iterator().next();
      GroupData group = groups.iterator().next();
      app.goTo().gotoHomePage();
      app.contact().selectGroupForDelete(group);
      app.contact().selectContactById(contact.getId());

      // app.contact().selectGroup(vacantGroup);
      app.contact().dellFromGroup();
      app.goTo().gotoHomePage();
    }
   // verifyContactListInUi();
    int i = 2;
  }

  public void toGroup(GroupData group) {

    // app.goTo().gotoHomePage();
    // app.contact().selectContactById(contact.getId());
    //  app.contact().selectGroup(vacantGroup);
    // app.contact().addToGroup();
  }
}


