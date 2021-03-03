package com.czareg;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraClient;
import net.rcarz.jiraclient.JiraException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WorkLogController {
    private static final String JIRA_URL = "https://czarecoo.atlassian.net/";
    private static final String JIRA_ADMIN_USERNAME = "czarecoo@o2.pl";
    private static final String JIRA_ADMIN_PASSWORD = "Wo9lwEbIlX3eHEr0zsR82839";

    @GetMapping
    public void get() throws JiraException {
        BasicCredentials creds = new BasicCredentials(JIRA_ADMIN_USERNAME, JIRA_ADMIN_PASSWORD);
        JiraClient jira = new JiraClient(JIRA_URL, creds);
        Issue issue = jira.getIssue("WL-1");
        System.out.println(issue.getDescription());
    }
}
