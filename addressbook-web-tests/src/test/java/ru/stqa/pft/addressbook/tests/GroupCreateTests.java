package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreateTests extends TestBase {


  @Test// (enabled = false)
  public void testGroupCreation2() {
    app.goTo().groupPage();
    Groups before =  app.group().all();

    GroupData group = new GroupData().withName("text1");
    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size()+1));
    Groups after =  app.group().all();

    assertThat(after, equalTo(
            before.withAdded( group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadGroupCreation2() {
    app.goTo().groupPage();
    Groups before =  app.group().all();

    GroupData group = new GroupData().withName("text1' ");
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size()));
    Groups after =  app.group().all();
    

    assertThat(after, equalTo(before));
  }
}
