package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupModificationTests extends TestBase {

  @Test
  public void testGroupModification(){
    app.getNavigationHelper().gotoGroupPage();

    app.getGroupHelper().secectGroup();
    app.getGroupHelper().initGruopModification();
    app.getGroupHelper().fillGroupCreation(new GroupData("text132", "text232", "text332"));
    app.getGroupHelper().submitGruopModification();
    app.getGroupHelper().returnToGroupPage();
  }


}
