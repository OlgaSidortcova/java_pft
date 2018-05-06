package ru.stqa.pft.addressbook.tests;

import org.hamcrest.CoreMatchers;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.Set;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupDeletionTests extends TestBase {


  @BeforeMethod
  public void ensurePreconditions() {
    app.goTo().groupPage();

    if ( app.group().all().size() == 0) {
      //(!app.group().isThereAGroup()    }) {

      app.group().create(new GroupData().withName("test1").withFooter("test3"));
    }

  }

  @Test
  public void testGroupDeletion()  {

    Groups before = app.group().all();

    GroupData deletedGroup = before. iterator().next();
    //int index = 0;

    app.group().delete(deletedGroup);

    Groups after = app.group().all();
    Assert.assertEquals(before.size() - 1, after.size());

    //before.remove(deletedGroup);

    //Comparator<? super GroupData> ById = (g1, g2) -> Integer.compare(g1.getId(), g2.getId());
   // before.sort(ById);
   // after.sort(ById);

    //Assert.assertEquals(before, after);

    assertThat(after, equalTo(before.without(deletedGroup)));

  }


}
