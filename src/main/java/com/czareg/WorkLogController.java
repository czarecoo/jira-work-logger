package com.czareg;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class WorkLogController {
    private static final String JIRA_URL = "https://czarecoo.atlassian.net/";
    private static final String JIRA_ADMIN_USERNAME = "czarecoo@o2.pl";
    private static final String JIRA_ADMIN_PASSWORD = "Wo9lwEbIlX3eHEr0zsR82839";

    @GetMapping
    public void get() {
        JiraRestClient jiraRestClient = new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(URI.create(JIRA_URL), JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD);
        Issue issue = jiraRestClient.getIssueClient().getIssue("WL-1").claim();
        System.out.println(issue.getDescription());
    }
}