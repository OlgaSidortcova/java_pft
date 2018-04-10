package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

public class GroupCreateTests extends TestBase {

  @Test
  public void testGroupCreation() {

    gotoGroupPage();
    initGroupCreation();
    fillGroupCreation(new GroupData("test1", "test2", "test3"));
    submitGroupCreation();
    gotoGroupPage();
  }

  @Test
  public void testGroupCreation2() {

    gotoGroupPage();
    initGroupCreation();
    fillGroupCreation(new GroupData("text1", "text2", "text3"));
    submitGroupCreation();
    gotoGroupPage();
  }

  @Test
  public void testGroupCreation3() {

    gotoGroupPage();
    initGroupCreation();
    fillGroupCreation(new GroupData("testName", "testHeader", "testFooter"));
    submitGroupCreation();
    gotoGroupPage();
  }


}
