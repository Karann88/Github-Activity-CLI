package com.github.cli;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

public class JsonParser {

    private final Gson gson = new Gson();

    public GitHubEvent[] parseEvents(String jsonResponse) throws JsonSyntaxException {
        return gson.fromJson(jsonResponse, GitHubEvent[].class);
    }
}
