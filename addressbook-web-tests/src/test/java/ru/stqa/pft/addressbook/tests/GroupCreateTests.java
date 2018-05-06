package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreateTests extends TestBase {

  @Test(enabled = false)
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));

  }

  @Test
  public void testGroupCreation2() {
    app.getNavigationHelper().gotoGroupPage();
    List<GroupData> before = app.getGroupHelper().getGroupList();

    GroupData group = new GroupData("text1", null, null);
    app.getGroupHelper().createGroup(group);


    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size() + 1, after.size());
    before.add(group);

    Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);

    Assert.assertEquals(before, after);
  }

  @Test(enabled = false)
  public void testGroupCreation3() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupCreation(new GroupData("testName", "testHeader", "testFooter"));
    app.getGroupHelper().submitGroupCreation();
    app.getNavigationHelper().gotoGroupPage();
  }

}
