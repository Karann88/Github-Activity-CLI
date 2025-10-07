package com.github.cli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "Github-Activity-CLI",
        mixinStandardHelpOptions = true,
        version = "1.0-SNAPSHOT",
        description = "Fetches and displays recent GitHub user activity."
)
public class CommandHandler implements Runnable {
    @CommandLine.Option(
            names = {"-u", "--user"},
            description = "GitHub username to fetch activity for.",
            required = true
    )
    private String username;

    @Override
    public void run() {
        System.out.println("Fetching recent activity for user: " + username + "...");
        ApiService apiService = new ApiService(); // Create an instance of ApiService
        JsonParser jsonParser = new JsonParser(); // Create an instance of JsonParser

        try {
            String jsonResponse = apiService.fetchUserEvents(username);
            var events = jsonParser.parseEvents(jsonResponse);
            Utils.displayEvents(events);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
