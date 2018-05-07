package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreateTests extends TestBase {


  @Test (enabled = false)
  public void testGroupCreation2() {
    app.goTo().groupPage();
    Groups before =  app.group().all();

    GroupData group = new GroupData().withName("text1");
    app.group().create(group);

    Groups after =  app.group().all();
    assertThat(before.size() + 1, equalTo(after.size()));

    assertThat(after, equalTo(
            before.withAdded( group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testGroupCreation3() {

    app.goTo().groupPage();
    app.group().initGroupCreation();
   //???? app.group().fillGroupCreation(new GroupData("testName", "testHeader", "testFooter"));
    app.group().submitGroupCreation();
    app.goTo().groupPage();
  }

}
