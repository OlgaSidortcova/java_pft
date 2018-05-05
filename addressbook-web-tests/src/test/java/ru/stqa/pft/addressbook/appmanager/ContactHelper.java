package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.util.ArrayList;
import java.util.List;

public class ContactHelper extends HelperBase {


  public ContactHelper(WebDriver wd) {

    super(wd);

  }


  public void fillNewContact(NewContactData newContactData, boolean creation) {

    type(By.name("firstname"), newContactData.getFirst_name());
    type(By.name("lastname"), newContactData.getLast_name());
    type(By.name("company"), newContactData.getCompany());
    type(By.name("address"), newContactData.getAddress());

    type(By.name("home"), newContactData.getHome());
    type(By.name("mobile"), newContactData.getMobile());
    type(By.name("work"), newContactData.getWork());

    type(By.name("fax"), newContactData.getFax());
    type(By.name("email"), newContactData.getEmail());


    if (creation) {
      if (newContactData.getGruop() == null) {
        type(By.name("new_group"), null);
      } else
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactData.getGruop());

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }

  }

  public void initNewContactCreation() {

    click(By.linkText("add new"));
  }

  public void submitNewContactCreation() {

    click(By.xpath("//div[@id='content']/form/input[21]"));
  }

  public void editContact(int index) {

    wd.findElements(By.xpath("//table[@id='maintable']/tbody/tr/td[8]/a/img")).get(index).click();

  }

  public void submitContactModification() {

    click(By.name("update"));

  }

  public void selectContact(int index) {

    wd.findElements(By.name("selected[]")).get(index).click();

  }

  public void deleteSelectedContacts() {

    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));

  }

  public void submitDeletionContact() {

    swich();
  }


  public boolean isThereAContact() {
    return isElementPresent(By.name("selected[]"));

  }

  public int getContactCount() {
    return wd.findElements(By.name("selected[]")).size();

  }

  public List<NewContactData> getContactList() {
    List<NewContactData> contacts = new ArrayList<NewContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));

    for (WebElement element : elements) {

      List<WebElement> cells = element.findElements(By.tagName("td"));
      String first_name = cells.get(2).getText();
      String last_name = cells.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

     // int id = Integer.parseInt(element.findElement(By.xpath(".//input[@name = selected[]']")).getAttribute("id"));


      NewContactData contact = new NewContactData(first_name, last_name, null, null, null, null, null, null, null, null, id);
      contacts.add(contact);
    }


    return contacts;
  }
}
