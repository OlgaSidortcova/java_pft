package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.addressbook.model.GroupData;

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

  public void secectGroup(int index) {

    wd.findElements(By.name("selected[]")).get(index).click();
   // click(By.name("selected[]"));
  }

  public void initGruopModification() {

    click(By.name("edit"));
  }

  public void submitGruopModification() {
    click(By.name("update"));
  }

  public void createGroup(GroupData group) {

    initGroupCreation();
    fillGroupCreation(group);

    submitGroupCreation();
    returnToGroupPage();
  }


  public boolean isThereAGroup() {
    return isElementPresent(By.name("selected[]"));
  }

  public int getGroupCount() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
