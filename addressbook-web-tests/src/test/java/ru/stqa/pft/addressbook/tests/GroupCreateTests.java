package ru.stqa.pft.addressbook.tests;

import com.thoughtworks.xstream.XStream;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class GroupCreateTests extends TestBase {

  @DataProvider
  public Iterator<Object[]> validGroups() throws IOException {

    String xml = "";

    BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.xml")));
    String line = reader.readLine();
    while (line != null) {
      xml += line;

      line = reader.readLine();
    }
    XStream xstream = new XStream();
    xstream.processAnnotations(GroupData.class);
    List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
    return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();

  }


  @Test(dataProvider = "validGroups")// (enabled = false)
  public void testGroupCreation2(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();

    app.group().create(group);
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(enabled = false)
  public void testBadGroupCreation2() {
    app.goTo().groupPage();
    Groups before = app.group().all();

    GroupData group = new GroupData().withName("text1' ");
    app.group().create(group);

    assertThat(app.group().count(), equalTo(before.size()));
    Groups after = app.group().all();

    assertThat(after, equalTo(before));
  }
}
