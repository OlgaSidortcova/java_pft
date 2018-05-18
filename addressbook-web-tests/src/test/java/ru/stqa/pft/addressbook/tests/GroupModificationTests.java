package ru.stqa.pft.addressbook.tests;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {

      app.goTo().groupPage();
      app.group().create(new GroupData().withName("23").withHeader("test23").withFooter("test32"));
    }

/*
    app.goTo().groupPage();

    if (app.group().all().size() == 0) {

      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }

    */
  }


  @Test//(enabled = false)
  public void testGroupModification() {
    // Groups before = app.group().all();

    Groups before = app.db().groups();

    GroupData modifiedGroup = before.iterator().next();

    GroupData group = new GroupData().
            withName("test1").withHeader("test2").withFooter("test3").withId(modifiedGroup.getId());
    app.goTo().groupPage();
    app.group().modify(group);
    assertThat(app.group().count(), equalTo(before.size()));


    // Groups after = app.group().all();
    Groups after = app.db().groups();


    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

  }


}
