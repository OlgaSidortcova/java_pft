package ru.stqa.pft.addressbook.tests;


import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    if (app.db().groups().size() == 0) {

      app.goTo().groupPage();
      app.group().create(new GroupData().withName("test1").withFooter("test3"));
    }


   /*
    app.goTo().groupPage();

    if (app.group().all().size() == 0) {
      app.group().create(new GroupData().withName("test1").withFooter("test3"));
    }
*/
  }

  @Test// (enabled = false)
  public void testGroupDeletion() {
    Groups before = app.db().groups();
    //Groups before = app.group().all();

    GroupData deletedGroup = before.iterator().next();
    app.goTo().groupPage();
    app.group().delete(deletedGroup);

    assertThat(app.group().count(), equalTo(before.size() - 1));
   // app.goTo().groupPage();
    Groups after = app.db().groups();
   // Groups after = app.group().all();
    // Assert.assertEquals(before.size() - 1, after.size());

    assertThat(after, equalTo(before.without(deletedGroup)));

  }


}
