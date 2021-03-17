package com.czareg.controller;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInput;
import com.atlassian.jira.rest.client.api.domain.input.WorklogInputBuilder;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.czareg.request.Request;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.time.DayOfWeek;
import java.util.EnumSet;

@RestController
public class WorkLogController {
    private static final int WORK_TIME_IN_MINUTES = 8 * 60;
    private EnumSet<DayOfWeek> workDays = EnumSet.range(DayOfWeek.MONDAY, DayOfWeek.FRIDAY);

    @PostMapping("/logEightHoursEveryWorkdayThisWeek")
    public void logEightHoursEveryWorkdayThisWeek(@RequestBody Request request) throws IOException {
        URI serverUri = URI.create(request.getUrl());
        String email = request.getEmail();
        String token = request.getToken();
        try (JiraRestClient jiraRestClient = new AsynchronousJiraRestClientFactory()
                .createWithBasicHttpAuthentication(serverUri, email, token)) {
            String issueKey = request.getIssueKey();
            Issue issue = jiraRestClient.getIssueClient().getIssue(issueKey).claim();
            URI workLogUri = issue.getWorklogUri();

            DateTime today = DateTime.now();
            DateTime todayAtNineAm = today.withTimeAtStartOfDay().plusHours(9);
            DateTime mondayAtNineAm = findMonday(todayAtNineAm);

            DateTime dayOfTheWeekAtNineAm = mondayAtNineAm;
            while (isWorkDay(dayOfTheWeekAtNineAm)) {
                WorklogInput worklogInput = new WorklogInputBuilder(workLogUri)
                        .setMinutesSpent(WORK_TIME_IN_MINUTES)
                        .setStartDate(dayOfTheWeekAtNineAm)
                        .build();
                jiraRestClient.getIssueClient().addWorklog(workLogUri, worklogInput).claim();
                dayOfTheWeekAtNineAm = dayOfTheWeekAtNineAm.plusDays(1);
            }
        }
    }

    private boolean isWorkDay(DateTime dayOfTheWeekAtNineAm) {
        return workDays.contains(DayOfWeek.of(dayOfTheWeekAtNineAm.getDayOfWeek()));
    }

    private DateTime findMonday(DateTime dateTime) {
        while (DayOfWeek.of(dateTime.getDayOfWeek()) != DayOfWeek.MONDAY) {
            dateTime = dateTime.minusDays(1);
        }
        return dateTime;
    }
}