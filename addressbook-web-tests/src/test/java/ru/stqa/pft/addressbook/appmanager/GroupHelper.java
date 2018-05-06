package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GroupHelper extends HelperBase {

  public GroupHelper(WebDriver wd) {
    super(wd);

  }

  public void submitGroupCreation() {
    click(By.name("submit"));
  }

  public void fillGroupCreation(GroupData groupData) {
    type(By.name("group_name"), groupData.getName());

    type(By.name("group_header"), groupData.getHeader());

    type(By.name("group_footer"), groupData.getFooter());

  }

  public void initGroupCreation() {
    click(By.name("new"));
  }

  public void returnToGroupPage() {
    click(By.linkText("group page"));
  }


  public void deleteSelectedGroups() {
    click(By.name("delete"));
  }

 /* public void secectGroup(int index) {
    wd.findElements(By.name("selected[]")).get(index).click();
  }
*/
  public void secectGroupById( int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "' ]")).click();

   // )name("selected[]")).get(id).click();
  }

  public void initGruopModification() {

    click(By.name("edit"));
  }

  public void submitGruopModification() {
    click(By.name("update"));
  }

  public void create(GroupData group) {

    initGroupCreation();
    fillGroupCreation(group);

    submitGroupCreation();
    returnToGroupPage();
  }
  public void modify(GroupData group) {
    secectGroupById(group.getId());
    initGruopModification();


    fillGroupCreation(group);
    submitGruopModification();
    returnToGroupPage();
  }
/*
  public void delete(int index) {
    secectGroup(index);
    deleteSelectedGroups();
    returnToGroupPage();
  }
*/
  public void delete(GroupData group) {
    secectGroupById(group.getId());
    deleteSelectedGroups();
    returnToGroupPage();

  }


  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }

/*  public List<GroupData> list() {
    List<GroupData> groups = new ArrayList<GroupData>();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

    for (WebElement element : elements) {

      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
    //  GroupData group = new GroupData().withName(name).withId(id);
      groups.add(new GroupData().withName(name).withId(id));
    }
    return groups;
  }
*/
  public Groups all() {

   Groups groups = new Groups();
    List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));

    for (WebElement element : elements) {

      String name = element.getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

      groups.add(new GroupData().withName(name).withId(id));
    }
    return groups;
  }


}
