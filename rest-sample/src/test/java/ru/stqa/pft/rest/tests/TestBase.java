package ru.stqa.pft.rest.tests;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import org.apache.http.client.fluent.Executor;
import org.apache.http.client.fluent.Request;
import org.testng.SkipException;

import java.io.IOException;

public class TestBase {

  public boolean isIssueOpen(int issueId) throws IOException {

    String status = getIssueStatus(issueId);
    return status.equals("Open");

  }

  public void skipIfNotFixed(int issueId) throws IOException {

    if (!isIssueOpen(issueId)) {
      System.out.println("Ignored because of issue " + issueId);
      throw new SkipException("Ignored because of issue " + issueId);
    }
  }

  private Executor getExecutor() {
    return Executor.newInstance().
            auth("68aeeff4613ab963ac9cf27929b6a6be", "");
  }

  private String getIssueStatus(int issueId) throws IOException {
    String json = getExecutor().execute(Request.Get(String.format("http://demo.bugify.com/api/issues/%s.json", issueId))).
            returnContent().asString();

    JsonElement parsed = new JsonParser().parse(json);
    JsonElement issues = parsed.getAsJsonObject().get("issues");
    JsonElement issue = issues.getAsJsonArray().get(0);
    String s =issue.getAsJsonObject().get("state_name").getAsString();
    System.out.println("   " + s);
    return s;

  }
}
