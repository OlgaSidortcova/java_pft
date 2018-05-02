package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.List;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();


    if (! app.getGroupHelper().isThereAGroup()){

      app.getGroupHelper().createGroup(new GroupData("test1", "test2", "test3"));

    }

  //  int before = app.getGroupHelper().getGroupCount();
    List<GroupData> before = app.getGroupHelper().getGroupList();
    app.getGroupHelper().secectGroup(before.size() - 1);
    app.getGroupHelper().initGruopModification();
    app.getGroupHelper().fillGroupCreation(new GroupData("text132", "text232", "text332"));
    app.getGroupHelper().submitGruopModification();
    app.getGroupHelper().returnToGroupPage();

    //int after = app.getGroupHelper().getGroupCount();
    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size(), after.size());
  }


}
