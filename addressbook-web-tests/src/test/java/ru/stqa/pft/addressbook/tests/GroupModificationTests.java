package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.testng.Assert.assertEquals;

public class GroupModificationTests extends TestBase {

  @BeforeMethod
  public void ensurePreconditions() {

    app.goTo().groupPage();

    if (app.group().all().size() == 0) {

      //  !app.group().isThereAGroup()) {
      app.group().create(new GroupData().withName("test1").withHeader("test2").withFooter("test3"));
    }
  }


  @Test
  public void testGroupModification() {
    Groups before = app.group().all();
    GroupData modifiedGroup = before. iterator().next();

    //int index = before.size() - 1;
    //  GroupData group = new GroupData("text132", "text232", "text332", before.get(index).getId());
    GroupData group = new GroupData().
            withName("test1").withHeader("test2").withFooter("test3").withId(modifiedGroup.getId());

    app.group().modify( group);

    Groups after = app.group().all();
    assertEquals(before.size(), after.size());

   // before.remove(modifiedGroup);
    //before.add(group);

   // Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
  //  before.sort(ById);
   // after.sort(ById);
    //Assert.assertEquals(before, after);
    assertThat(after, equalTo(before.without(modifiedGroup).withAdded(group)));

  }


}
