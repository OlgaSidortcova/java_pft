package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class ChangePasswordTest extends TestBase {

  @Test
  public void testChangePassword() throws IOException, MessagingException {

    long now = System.currentTimeMillis();
    String email = String.format("user%s@localhost", now);
    String user = String.format("user%s", now);
    String password = "password";
    String passwordNew = "passwordNew";

    //
    app.james().createUser(user, password);
    app.registration().start(user, email);
    List<MailMessage> mailMessages = app.james().waitForMail(user, password, 60000);
    String confirmationLink = findConfirmationLink(mailMessages, email);
    app.registration().finish(confirmationLink, passwordNew);
    //
    app.registration().init("administrator", "root");
    app.registration().selectUser(user);

    String email1 = app.registration().getEmail();
    String user1 = app.registration().getUser();

    app.registration().resetPassword();
    List<MailMessage> mailMessages1 = app.james().waitForMail(user1, password, 60000);

    String confirmationLink1 = findConfirmationLink(mailMessages1, email1);
    app.registration().logout();

    app.registration().finish(confirmationLink1, passwordNew);
    assertTrue(app.newSession().login(user1, passwordNew));
  }

  private String findConfirmationLink(List<MailMessage> mailMessages, String email) {
int i =mailMessages.size();
    MailMessage mailMessage1 = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();

    MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email))
            .reduce((first, second) -> second).get();

    VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
    return regex.getText(mailMessage.text);
  }
}
