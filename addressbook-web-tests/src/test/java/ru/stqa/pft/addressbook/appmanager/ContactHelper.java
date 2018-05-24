package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContactData;

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
    type(By.name("email2"), newContactData.getEmail2());
    type(By.name("email3"), newContactData.getEmail3());
    attach(By.name("photo"), newContactData.getPhoto());

    if (newContactData.getFirst_name() == null) {
      newContactData.withFirst_name("");
    }
    if (newContactData.getLast_name() == null) {
      newContactData.withLast_name("");
    }

    if (newContactData.getCompany() == null) {
      newContactData.withCompany("");
    }
    if (newContactData.getAddress() == null) {
      newContactData.withAddress("");
    }

    if (newContactData.getHome() == null) {
      newContactData.withHome("");
    }
    if (newContactData.getMobile() == null) {
      newContactData.withMobile("");
    }
    if (newContactData.getWork() == null) {
      newContactData.withWork("");
    }

    if (newContactData.getFax() == null) {
      newContactData.withFax("");
    }
    if (newContactData.getEmail() == null) {
      newContactData.withEmail("");
    }
    if (newContactData.getEmail2() == null) {
      newContactData.withEmail2("");
    }
    if (newContactData.getEmail3() == null) {
      newContactData.withEmail3("");
    }
/*
    if (creation) {
      if (newContactData.getGroup() == null) {
        type(By.name("new_group"), null);
      } else
        new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(newContactData.getGruop());

    } else {
      Assert.assertFalse(isElementPresent(By.name("new_group")));
    }
    */
    //if (newContactData.getGroups() == null) {
    //   System.out.println("newContactData.getGroups()");
//
    //  }
    // System.out.println(newContactData.getGroups());
    //   Groups g = newContactData.getGroups();
    //  System.out.println(newContactData.getGroups());

    // int i = newContactData.getGroups().size();

    if (creation) {
      if (newContactData.getGroups().size() > 0) {
        Assert.assertTrue(newContactData.getGroups().size() == 1);
        new Select(wd.findElement(By.name("new_group"))).
                selectByVisibleText(newContactData.getGroups().iterator().next().getName());

        // type(By.name("new_group"), null);
        // } else
        //  new Select(wd.findElement(By.name("new_group"))).
        //          selectByVisibleText(newContactData.getGroups().iterator().next().getName());

      } else type(By.name("new_group"), null);

    } else Assert.assertFalse(isElementPresent(By.name("new_group")));
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

  private Contacts contactCashe = null;

  public Contacts all() {

    if (contactCashe != null) {
      return new Contacts(contactCashe);
    }

    contactCashe = new Contacts();
    List<WebElement> elements = wd.findElements(By.xpath("//tr[@name='entry']"));

    for (WebElement element : elements) {

      List<WebElement> cells = element.findElements(By.tagName("td"));
      String firstName = cells.get(2).getText();
      String lastName = cells.get(1).getText();
      String allPhones = cells.get(5).getText();
      String address = cells.get(3).getText();
      String allEmails = cells.get(4).getText();

      int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));

      contactCashe.add(new NewContactData().withFirst_name(firstName).withLast_name(lastName).withId(id).
              withAllPhones(allPhones).withAddress(address).withAllEmails(allEmails));
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
    String email = wd.findElement(By.name("email")).getAttribute("value");
    String email2 = wd.findElement(By.name("email2")).getAttribute("value");
    String email3 = wd.findElement(By.name("email3")).getAttribute("value");
    String address = wd.findElement(By.name("address")).getAttribute("value");

    wd.navigate().back();
    return new NewContactData().withId(contact.getId()).withFirst_name(firstname).withLast_name(lastname).
            withHome(homephone).withMobile(mobilephone).withWork(workphone).
            withEmail(email).withEmail2(email2).withEmail3(email3).withAddress(address);
  }

  private void initContactModificationById(int id) {
    wd.findElement(By.xpath(String.format("//input[@value='%s']/../../td[8]/a", id))).click();
  }

  public int count() {
    return wd.findElements(By.name("selected[]")).size();
  }
}
