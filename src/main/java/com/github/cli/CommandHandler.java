package com.github.cli;

import picocli.CommandLine;

@CommandLine.Command(
        name = "github-activity-viewer",
        mixinStandardHelpOptions = true,
        version = "1.0.0",
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
        ApiService apiService = new ApiService();
    }
}
