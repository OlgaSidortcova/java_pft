package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupCreateTests extends TestBase {

  @Test (enabled = false)
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));

  }


  @Test
  public void testGroupCreation2() {
    app.getNavigationHelper().gotoGroupPage();

    int before = app.getGroupHelper().getGroupCount();

    app.getGroupHelper().createGroup(new GroupData("text1", null, null));

    int after = app.getGroupHelper().getGroupCount();

    Assert.assertEquals(before + 1, after);

  }



  @Test (enabled = false)
  public void testGroupCreation3() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().initGroupCreation();
    app.getGroupHelper().fillGroupCreation(new GroupData("testName", "testHeader", "testFooter"));
    app.getGroupHelper().submitGroupCreation();
    app.getNavigationHelper().gotoGroupPage();
  }


}
