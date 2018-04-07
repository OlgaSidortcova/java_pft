package ru.stqa.pft.addressbook;

import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;


  public class NewContactCreateTests {
    FirefoxDriver wd;

    public static boolean isAlertPresent(FirefoxDriver wd) {
      try {
        wd.switchTo().alert();
        return true;
      } catch (NoAlertPresentException e) {
        return false;
      }
    }

    @BeforeMethod
    public void setUp() throws Exception {

      wd = new FirefoxDriver(new FirefoxOptions().setLegacy(true).setBinary("/home/sadsido/Tools/firefox/firefox"));

      wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

      wd.get("http://localhost/addressbook/index.php");
      login("admin", "secret");
    }

    private void login(String usermane, String password) {
      wd.findElement(By.name("user")).click();
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(usermane);
      wd.findElement(By.id("LoginForm")).click();
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }

    @Test
    public void testNewContact() {

      initNewContactCreation();

      fillNewContact(new NewContactData("FirstName", "LastName", "Company", "Address", "home", "mobile", "work", "fax", "e-mail"));

      submitNewContactCreation();
      //wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    private void fillNewContact(NewContactData newContactData) {
      wd.findElement(By.name("firstname")).click();
      wd.findElement(By.name("firstname")).clear();
      wd.findElement(By.name("firstname")).sendKeys(newContactData.getFirst_name());
      wd.findElement(By.name("lastname")).click();
      wd.findElement(By.name("lastname")).clear();
      wd.findElement(By.name("lastname")).sendKeys(newContactData.getLast_name());
      wd.findElement(By.name("company")).click();
      wd.findElement(By.name("company")).clear();
      wd.findElement(By.name("company")).sendKeys(newContactData.getCompany());
      wd.findElement(By.name("address")).click();
      wd.findElement(By.name("address")).clear();
      wd.findElement(By.name("address")).sendKeys(newContactData.getAddress());
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("theform")).click();
      wd.findElement(By.name("home")).click();
      wd.findElement(By.name("home")).clear();
      wd.findElement(By.name("home")).sendKeys(newContactData.getHome());
      wd.findElement(By.name("mobile")).click();
      wd.findElement(By.name("mobile")).clear();
      wd.findElement(By.name("mobile")).sendKeys(newContactData.getMobile());
      wd.findElement(By.name("work")).click();
      wd.findElement(By.name("work")).clear();
      wd.findElement(By.name("work")).sendKeys(newContactData.getWork());
      wd.findElement(By.name("fax")).click();
      wd.findElement(By.name("fax")).clear();
      wd.findElement(By.name("fax")).sendKeys(newContactData.getFax());
      wd.findElement(By.name("email")).click();
      wd.findElement(By.name("email")).clear();
      wd.findElement(By.name("email")).sendKeys(newContactData.getEmail());
    }

    private void initNewContactCreation() {
      wd.findElement(By.linkText("add new")).click();
    }

    private void submitNewContactCreation() {
      wd.findElement(By.name("submit")).click();
    }

    @AfterMethod
    public void tearDown() {
      wd.quit();
    }
  }


