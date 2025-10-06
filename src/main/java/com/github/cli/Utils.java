package com.github.cli;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Utils {
    public static void displayEvents(GitHubEvent[] events) {
        if (events == null || events.length == 0) {
            System.out.println("No recent activity found.");
            return;
        }

        System.out.println("----------------------------------------");
        for (int i = 0; i < events.length; i++) {
            GitHubEvent event = events[i];
            System.out.printf("[%d] %s in %s at %s%n", i + 1, event.getType(), event.getRepo() != null ? event.getRepo().name : "Unknown Repo", event.getCreated_at());
        }
        System.out.println("----------------------------------------");
        System.out.println("Total Events: " + events.length);
    }

    public static String formatdate(String dateStr) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
            return dateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        } catch (Exception e) {
            return dateStr;
        }
    }
}
