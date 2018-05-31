package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

public class RegistrationHelper extends HelperBase {

  public RegistrationHelper(ApplicationManager app) {
     super(app);
  }

  public void start(String username, String email) {
    wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
     type(By.name("username"), username);
    type(By.name("email"), email);
    click(By.cssSelector("input[value='Signup']"));
  }

  public void finish(String confirmationLink, String password) {
    wd.get(confirmationLink);
    type(By.name("password"),password);
    type(By.name("password_confirm"),password);
    click(By.cssSelector("input[value='Update User']"));
  }

  public void init(String username, String password) {
    wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
    type(By.name("username"), username);
    type(By.name("password"), password);
    click(By.cssSelector("input[value='Login']"));
  }

  public void selectUser(String user) {
    click(By.cssSelector("a[href='/mantisbt-1.2.19/manage_user_page.php']"));
    click(By.xpath(String.format("//td[contains(text(),'%s')]/../td[1]/a", user)));
  }

  public String getEmail() {
   return wd.findElement(By.name("email")).getAttribute("value");
  }
  public String getUser() {
    return wd.findElement(By.name("username")).getAttribute("value");
  }
  public void resetPassword() {
    click(By.cssSelector("input[value='Reset Password']"));

  }
  public void logout() {
    click(By.cssSelector("a[href='/mantisbt-1.2.19/logout_page.php']"));
  }
}

