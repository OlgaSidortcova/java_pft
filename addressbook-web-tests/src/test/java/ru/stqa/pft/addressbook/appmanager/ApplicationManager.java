package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

  FirefoxDriver wd;


  private SessionHelper sessionHelper;
  private NavigationHelper navigationHelper;
  private GroupHelper groupHelper;



  public void init() {
    wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/sadsido/Tools/firefox/firefox"));
    wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    wd.get("http://localhost/addressbook/index.php");

    navigationHelper = new NavigationHelper(wd);
    groupHelper = new GroupHelper(wd);
    sessionHelper = new SessionHelper(wd);

    sessionHelper.login("admin", "secret");
  }

  public void stop() {

    wd.quit();
  }

  public void fillNewContact(NewContactData newContactData) {
    groupHelper.ReturnToGroupPage(By.name("firstname"));
    wd.findElement(By.name("firstname")).clear();
    wd.findElement(By.name("firstname")).sendKeys(newContactData.getFirst_name());
    groupHelper.ReturnToGroupPage(By.name("lastname"));
    wd.findElement(By.name("lastname")).clear();
    wd.findElement(By.name("lastname")).sendKeys(newContactData.getLast_name());
    groupHelper.ReturnToGroupPage(By.name("company"));
    wd.findElement(By.name("company")).clear();
    wd.findElement(By.name("company")).sendKeys(newContactData.getCompany());
   wd.findElement(By.name("address")).clear();
    wd.findElement(By.name("address")).sendKeys(newContactData.getAddress());
    groupHelper.ReturnToGroupPage(By.name("home"));
    groupHelper.ReturnToGroupPage(By.name("theform"));
    groupHelper.ReturnToGroupPage(By.name("home"));
    wd.findElement(By.name("home")).clear();
    wd.findElement(By.name("home")).sendKeys(newContactData.getHome());
    groupHelper.ReturnToGroupPage(By.name("mobile"));
   wd.findElement(By.name("mobile")).clear();
    wd.findElement(By.name("mobile")).sendKeys(newContactData.getMobile());
    groupHelper.ReturnToGroupPage(By.name("work"));
    wd.findElement(By.name("work")).clear();
    wd.findElement(By.name("work")).sendKeys(newContactData.getWork());
    groupHelper.ReturnToGroupPage(By.name("fax"));
    wd.findElement(By.name("fax")).clear();
    wd.findElement(By.name("fax")).sendKeys(newContactData.getFax());
    groupHelper.ReturnToGroupPage(By.name("email"));
   wd.findElement(By.name("email")).clear();
    wd.findElement(By.name("email")).sendKeys(newContactData.getEmail());
  }

  public void initNewContactCreation() {

    groupHelper.ReturnToGroupPage(By.linkText("add new"));
  }

  public void submitNewContactCreation() {

    groupHelper.ReturnToGroupPage(By.name("submit"));
  }

  public GroupHelper getGroupHelper() {
    return groupHelper;
  }

  public NavigationHelper getNavigationHelper() {
    return navigationHelper;
  }
}
