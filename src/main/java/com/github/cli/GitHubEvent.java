package com.github.cli;

public class GitHubEvent {
    private String type;
    private String created_at;
    private Actor actor;
    private Repo repo;

    static class Actor {
        String login;
        String avatar_url;
    }

    static class Repo {
        String name;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getType() {
        return type;
    }

    public Actor getActor() {
        return actor;
    }

    public Repo getRepo() {
        return repo;
    }
}
