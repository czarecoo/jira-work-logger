package com.czareg;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import io.atlassian.util.concurrent.Promise;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;

@RestController
public class WorkLogController {
    private static final String JIRA_URL = "https://czarecoo.atlassian.net/";
    private static final String JIRA_ADMIN_USERNAME = "czarecoo@o2.pl";
    private static final String JIRA_ADMIN_PASSWORD = "4funrulez";

    @GetMapping
    public void get() throws URISyntaxException {
        JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        URI uri = new URI(JIRA_URL);
        JiraRestClient client = factory.create(uri, new BasicHttpAuthenticationHandler(JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD));
        Promise<Issue> issuePromise = client.getIssueClient().getIssue("WL-1");
        Issue issue = issuePromise.claim();
        System.out.println(issue.getDescription());
    }
}
