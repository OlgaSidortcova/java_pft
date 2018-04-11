package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

public class NavigationHelper extends HelperBase{


  public NavigationHelper(FirefoxDriver wd) {
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

    //click(By.xpath("//div[@id='content']/form/input[21]"));

  }
}
