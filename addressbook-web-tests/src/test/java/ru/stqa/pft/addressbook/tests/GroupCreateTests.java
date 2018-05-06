package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupCreateTests extends TestBase {

  @Test(enabled = false)
  public void testGroupCreation() {

    app.goTo().groupPage();
   //???????? app.group().create(new GroupData("test1", null, "test3"));

  }

  @Test
  public void testGroupCreation2() {
    app.goTo().groupPage();
    List<GroupData> before = app.group().list();

  //  GroupData group = new GroupData("text1", null, null);
    GroupData group = new GroupData().withName("text1");
    app.group().create(group);


    List<GroupData> after = app.group().list();
    Assert.assertEquals(before.size() + 1, after.size());
    before.add(group);

    Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);

    Assert.assertEquals(before, after);
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
