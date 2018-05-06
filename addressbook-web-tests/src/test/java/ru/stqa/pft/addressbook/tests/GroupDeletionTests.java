package ru.stqa.pft.addressbook.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.Comparator;
import java.util.List;

public class GroupDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();

    if ( app.group().list().size() == 0) {
      //(!app.group().isThereAGroup()    }) {

      app.group().create(new GroupData().withName("test1").withFooter("test3"));
    }

  }

  @Test
  public void testGroupDeletion()  {

    List<GroupData> before = app.group().list();
    int index = 0;

    app.group().delete(index);

    List<GroupData> after = app.group().list();
    Assert.assertEquals(before.size() - 1, after.size());

    before.remove(index);

    Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
    before.sort(ById);
    after.sort(ById);
    Assert.assertEquals(before, after);
  }


}
