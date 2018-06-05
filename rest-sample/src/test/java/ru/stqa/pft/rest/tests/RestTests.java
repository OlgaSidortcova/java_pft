package ru.stqa.pft.rest.tests;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.apache.http.message.BasicNameValuePair;
import org.testng.annotations.Test;
import ru.stqa.pft.rest.model.Issue;

import java.io.IOException;
import java.util.Set;

import static org.testng.Assert.assertEquals;

public class RestTests extends TestBase {

  @Test
  public void testCreateIssue() throws IOException {

    skipIfNotFixed(1712);
    Set<Issue> oldIssues = getIssue();
    Issue newIssue = new Issue().withSubject("Test issue123465789").withDescription("test issue description");
    int issueId = createIssue(newIssue);
    System.out.println("Created issue id = "+ issueId);
      Set<Issue> newIssues = getIssue();
    oldIssues.add(newIssue.withId(issueId));
    assertEquals(oldIssues, newIssues);

  }

  private int createIssue(Issue newIssue) throws IOException {

    String json = getExecutor().execute(Request.Post("http://demo.bugify.com/api/issues.json").
            bodyForm(new BasicNameValuePair("subject", newIssue.getSubject()),
                    new BasicNameValuePair("description", newIssue.getDescription()))).
            returnContent().asString();

    JsonElement parsed = new JsonParser().parse(json);
    int i =parsed.getAsJsonObject().get("issue_id").getAsInt();
    System.out.println("   " + i);
    String s =parsed.getAsJsonObject().get("message").getAsString();
    System.out.println("   " + s);

    return parsed.getAsJsonObject().get("issue_id").getAsInt();


  }

  private Set<Issue> getIssue() throws IOException {
    String json = getExecutor().execute(Request.Get("http://demo.bugify.com/api/issues.json")).
            returnContent().asString();

    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");

    return new Gson().fromJson(issues, new TypeToken<Set<Issue>>() {
    }.getType());

  }

  private Executor getExecutor() {
    return Executor.newInstance().
            auth("68aeeff4613ab963ac9cf27929b6a6be", "");
  }
}
