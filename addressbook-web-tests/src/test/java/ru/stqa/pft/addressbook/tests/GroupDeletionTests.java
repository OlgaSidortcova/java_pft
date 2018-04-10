package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;

import org.openqa.selenium.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion()

  {
    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().SecectGroup("selected[]");
    app.getGroupHelper().DeleteSelectedGroup("delete");
    app.getGroupHelper().ReturnToGroupPage(By.linkText("group page"));
  }


}
