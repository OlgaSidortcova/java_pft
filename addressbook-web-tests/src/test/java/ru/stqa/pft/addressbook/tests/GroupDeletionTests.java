package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {

  @Test
  public void testGroupDeletion()

  {
    app.getNavigationHelper().gotoGroupPage();

    if (!app.getGroupHelper().isThereAGroup()) {

      app.getGroupHelper().createGroup(new GroupData("test1", null, "test3"));
    }
    List<GroupData> before = app.getGroupHelper().getGroupList();

    app.getGroupHelper().secectGroup(0);

    app.getGroupHelper().deleteSelectedGroups();

    app.getGroupHelper().returnToGroupPage();

    List<GroupData> after = app.getGroupHelper().getGroupList();
    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(0);

    Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }
}
