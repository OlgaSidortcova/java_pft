package ru.stqa.pft.addressbook.appmanager;

import com.sun.javafx.binding.StringFormatter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
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

  public void editContactById(int id) {

    wd.findElement(By.xpath("//table[@id='maintable']/tbody/tr/td/input[@value='" + id + "']/../../td[8]/a/img")).click();

  }

  public void submitContactModification() {

    click(By.name("update"));

  }

  public void selectContactById(int id) {
    wd.findElement(By.cssSelector("input[value = '" + id + "' ]")).click();

  }

  public void deleteSelectedContacts() {

    click(By.xpath("//div[@id='content']/form[2]/div[2]/input"));

  }

  public void submitDeletionContact() {

    swich();
  }

  public List<NewContactData> list() {
    List<NewContactData> contacts = new ArrayList<NewContactData>();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));

    for (WebElement element : elements) {

      List<WebElement> cells = element.findElements(By.tagName("td"));
      String first_name = cells.get(2).getText();
      String last_name = cells.get(1).getText();
      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

      contacts.add(new NewContactData().withFirst_name(first_name).withLast_name(last_name).withId(id));
    }
    return contacts;
  }

  private  Contacts contactCashe = null;


  public Contacts all() {

    if (contactCashe != null) {
      return  new Contacts(contactCashe);
    }

    contactCashe = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));

    for (WebElement element : elements) {

      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
     // String[] phones = cells.get(5).getText().split("\n");

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

     // contactCashe.add(new NewContactData().withFirst_name(firstName).withLast_name(lastName).
     //         withId(id).withHome(phones[0]).withMobile(phones[1]).withWork(phones[2]));
      contactCashe.add(new NewContactData().withFirst_name(firstName).withLast_name(lastName).withId(id).withAllPhones(allPhones));

    }
    return new Contacts(contactCashe);
  }


  public void create(NewContactData contact) {
    initNewContactCreation();
    fillNewContact(contact, true);

    submitNewContactCreation();
    contactCashe = null;
  }

  public void modify(NewContactData contact) {
    editContactById(contact.getId());
    fillNewContact(contact, false);
    submitContactModification();
    contactCashe = null;
  }

  public void delete(NewContactData contact) {
    selectContactById(contact.getId());
    deleteSelectedContacts();
    submitDeletionContact();
    contactCashe = null;
  }

  public NewContactData infoFromEditForm(NewContactData contact) {

    initContactModificationById(contact.getId());
    String firstname = wd.findElement(By.name("firstname")).getAttribute("value");
    String lastname = wd.findElement(By.name("lastname")).getAttribute("value");
    String homephone = wd.findElement(By.name("home")).getAttribute("value");
    String mobilephone = wd.findElement(By.name("mobile")).getAttribute("value");
    String workphone = wd.findElement(By.name("work")).getAttribute("value");
    wd.navigate().back();
    return new NewContactData().withId(contact.getId()).withFirst_name(firstname).withLast_name(lastname).
            withHome(homephone).withMobile(mobilephone).withWork(workphone);
  }

  private void initContactModificationById(int id) {

    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a",id))).click();
  }

  public int count(){

    return wd.findElements(By.name("selected[]")).size();
  }
}
