package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import ru.stqa.pft.addressbook.model.GroupData;

public class GroupHelper extends HelperBase {

  public GroupHelper(FirefoxDriver wd) {
super (wd);

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

  public void secectGroup() {
    click(By.name("selected[]"));
  }

  public void initGruopModification() {

    click(By.name("edit"));
  }

  public void submitGruopModification() {
    click(By.name("update"));
  }
}
