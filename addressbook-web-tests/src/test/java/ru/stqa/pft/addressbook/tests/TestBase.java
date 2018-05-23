package ru.stqa.pft.addressbook.tests;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;
import ru.stqa.pft.addressbook.model.NewContactData;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class TestBase {

  Logger logger = LoggerFactory.getLogger(TestBase.class);
  protected static final ApplicationManager app = new ApplicationManager(
          System.getProperty("browser", BrowserType.CHROME));

  @BeforeSuite
  public void setUp() throws Exception {
    app.init();
  }

  @AfterSuite(alwaysRun = true)
  public void tearDown() {
    app.stop();
  }

  @BeforeMethod
  public void logTestStart(Method m, Object[] p) {
    logger.info("Start test " + m.getName() + " with parameter " + Arrays.asList(p));
  }

  @AfterMethod(alwaysRun = true)
  public void logTestStop(Method m) {
    logger.info("Stop test " + m.getName());
  }

  public void veryfaiGroupListInUi() {

    if (Boolean.getBoolean("verifyUi")) {
      Groups dbGroups = app.db().groups();
      Groups uiGroups = app.group().all();

      assertThat(uiGroups, equalTo(dbGroups.stream().
              map((g) -> new GroupData().withName(g.getName()).withId(g.getId())).collect(Collectors.toSet())));
    }
  }

  public void veryfaiContactListInUi() {

    if (Boolean.getBoolean("verifyUi")) {
      Contacts dbContacts = app.db().contacts();
      Contacts uiContacts = app.contact().all();


      assertThat(uiContacts, equalTo(dbContacts.stream().
              map((g) -> new NewContactData().withFirst_name(g.getFirst_name()).withId(g.getId()).
                      withLast_name(g.getLast_name()).withAddress(g.getAddress()).
                      withAllEmails(mergeEmails(g)).withAllPhones(mergeEmails(g))
              ).collect(Collectors.toSet())));
    }
  }

  private String mergeEmails(NewContactData contact) {

    return Arrays.asList(contact.getEmail(), contact.getEmail2(), contact.getEmail3()).stream().filter((s) -> !s.equals("")).
            map(ContactEmailTests::cleaned).
            collect(Collectors.joining("\n"));
  }

  public static String cleaned(String email) {
    return email.replaceAll("\\s", "");
  }

}
