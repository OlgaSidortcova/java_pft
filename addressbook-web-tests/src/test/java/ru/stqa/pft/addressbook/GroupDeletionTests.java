package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

import org.openqa.selenium.*;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion()

  {
    gotoGroupPage();
    SecectGroup("selected[]");
    DeleteSelectedGroup("delete");
    ReturnToGroupPage(By.linkText("group page"));
  }


}
