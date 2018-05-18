package ru.stqa.pft.addressbook.tests;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

//  Logger logger = LoggerFactory.getLogger(GroupCreateTests.class);

  @DataProvider
  public Iterator<Object[]> validGroupsFromXml() throws IOException {

    String xml = "";
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.xml")));
    ) {
      //  BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.xml")));
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
  }

  @DataProvider
  public Iterator<Object[]> validGroupsFromJson() throws IOException {
    String json = "";
    try (BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.json")))) {

      // BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/group.json")));
      String line = reader.readLine();
      while (line != null) {
        json += line;

        line = reader.readLine();
      }
      Gson gson = new Gson();
      List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
      }.getType());// List<GroupData.class

      return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }
  }

  @Test(enabled = false)//(dataProvider = "validGroupsFromXml")// (enabled = false)
  public void testGroupCreation2(GroupData group) {
    app.goTo().groupPage();
    Groups before = app.group().all();

    app.group().create(group);
    app.goTo().groupPage();////
    assertThat(app.group().count(), equalTo(before.size() + 1));
    Groups after = app.group().all();

    assertThat(after, equalTo(
            before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
  }

  @Test(dataProvider = "validGroupsFromJson")// (enabled = false)
  public void testGroupCreation1(GroupData group) {

   app.goTo().groupPage();
    Groups before = app.group().all();

    app.group().create(group);
    app.goTo().groupPage();////
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
