package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class GroupCreateTests extends TestBase {

  @Test (enabled = false)
  public void testGroupCreation() {

    app.getNavigationHelper().gotoGroupPage();
    app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));

  }


  @Test
  public void testGroupCreation2() {
    app.getNavigationHelper().gotoGroupPage();

    List<GroupData> before = app.getGroupHelper().getGroupList();

   // int before = app.getGroupHelper().getGroupCount();


    GroupData group = new GroupData("text1", null, null);

    app.getGroupHelper().createGroup(group);


    List<GroupData> after = app.getGroupHelper().getGroupList();

    //int after = app.getGroupHelper().getGroupCount();

    Assert.assertEquals(before.size() + 1, after.size());

    int max = 0;

    for (GroupData g : after){
      if (g.getId()> max){
        max = g.getId();


      }
    }

  //  Comparator<? super GroupData> ById = (Comparator<GroupData>) (o1, o2) -> Integer.compare(o1.getId(), o2.getId());


    //int max1 = after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId();

group.setId(after.stream().max((o1, o2) -> Integer.compare(o1.getId(), o2.getId())).get().getId());
    before.add(group);

    Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

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
