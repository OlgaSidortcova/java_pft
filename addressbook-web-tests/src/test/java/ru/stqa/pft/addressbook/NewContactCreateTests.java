package ru.stqa.pft.addressbook;

import org.testng.annotations.Test;

  public class NewContactCreateTests extends TestBase{

   /*
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
*/
   /* private void login(String usermane, String password) {
      wd.findElement(By.name("user")).click();
      wd.findElement(By.name("user")).clear();
      wd.findElement(By.name("user")).sendKeys(usermane);
      wd.findElement(By.id("LoginForm")).click();
      wd.findElement(By.name("pass")).click();
      wd.findElement(By.name("pass")).clear();
      wd.findElement(By.name("pass")).sendKeys(password);
      wd.findElement(By.xpath("//form[@id='LoginForm']/input[3]")).click();
    }
*/
    @Test
    public void testNewContact() {

      initNewContactCreation();
      fillNewContact(new NewContactData("FirstName", "LastName", "Company", "Address", "home", "mobile", "work", "fax", "e-mail"));
      submitNewContactCreation();
      //wd.findElement(By.xpath("//div[@id='content']/form/input[21]")).click();
    }

    @Test
    public void testNewContact2() {

      initNewContactCreation();
      fillNewContact(new NewContactData("test1", "test2", "test3", "test4", "test5", "test6", "test7", "test8", "test9"));
      submitNewContactCreation();

    }



    /*
    @AfterMethod
    public void tearDown() {
      wd.quit();
    }

    */
  }


