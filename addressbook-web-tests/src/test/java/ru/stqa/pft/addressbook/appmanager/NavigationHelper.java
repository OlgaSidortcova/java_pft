package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NavigationHelper extends HelperBase{


  public NavigationHelper(WebDriver wd) {
    super (wd);
  }

  public void gotoGroupPage() {

    click(By.linkText("groups"));
  }

  public void returnToHomePage() {
    click(By.linkText("home page"));
  }


  public void addNextContact() {

     click(By.linkText("add next"));

  }

  public void gotoHomePage() {
    click(By.linkText("home"));
  }
}
