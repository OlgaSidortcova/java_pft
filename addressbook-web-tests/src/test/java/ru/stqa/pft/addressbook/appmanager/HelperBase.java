package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class HelperBase {


  FirefoxDriver wd;

  public HelperBase(FirefoxDriver wd) {
    this.wd = wd;
  }

  protected void click(By locator) {
    wd.findElement(locator).click();
  }

  protected void swich() {

    wd.switchTo().alert().accept();

  }



  protected void type(By locator, String text) {
    click(locator);
    wd.findElement(locator).clear();
    wd.findElement(locator).sendKeys(text);
  }

/*
  protected void selectElement(By locator) {

    if (!wd.findElement(locator).isSelected()) {
      wd.findElement(locator).click();
    }

  }
*/

  public boolean isAlertPresent() {
    try {
      wd.switchTo().alert();
      return true;

    } catch (NoAlertPresentException e) {
      return false;
    }
  }
}
