# Github-Activity-CLI

A small Java command-line tool that fetches and displays recent public GitHub activity for a user.

This project calls the GitHub Events API for a given username and prints a summarized list of recent events (type, repository and timestamp).

## Features

- Fetches public events for a GitHub user using the GitHub REST API
- Parses JSON event payloads with Gson
- Simple, focused CLI built with picocli

## Prerequisites

- Java 23 (the project is configured to compile for Java 23)
- Maven 3.x
- Internet access to call the GitHub API

Note: The project uses the following notable dependencies (declared in `pom.xml`):

- Apache HttpClient 5 (httpclient5) for HTTP calls
- Gson for JSON parsing
- picocli for CLI parsing

## Build

From the project root (where `pom.xml` is located) run:

```powershell
mvn clean package
```

This will produce a jar in the `target/` folder (for example `Github-Activity-CLI-1.0-SNAPSHOT.jar`).

## Run

There are two easy ways to run the CLI:

- Using `mvn exec:java` (requires Maven Exec plugin configuration — not included by default)
- Running the built jar directly

Example (run the jar generated in `target/`):

```powershell
java -jar target\Github-Activity-CLI-1.0-SNAPSHOT.jar -u <github-username>
```

Replace `<github-username>` with the username you want to inspect. Example:

```powershell
java -jar target\Github-Activity-CLI-1.0-SNAPSHOT.jar -u torvalds
```

## Usage

The CLI uses picocli. The main option is:

- `-u`, `--user` (required): GitHub username to fetch activity for.

You can also use standard picocli help/version options:

- `-h`, `--help`: display help
- `-V`, `--version`: display version

Example output

```
Fetching recent activity for user: torvalds...
----------------------------------------
[1] PushEvent in linux at 2023-08-20T14:52:11Z
[2] IssueCommentEvent in some-repo at 2023-08-19T12:05:40Z
----------------------------------------
Total Events: 2
```

## Implementation notes

- The CLI entry point is `com.github.cli.Main` which uses picocli to wire `CommandHandler`.
- `ApiService` constructs and performs the GET request to `https://api.github.com/users/{username}/events` and returns the raw JSON string.
- `JsonParser` uses Gson to deserialize the JSON into an array of `GitHubEvent` objects.
- `Utils.displayEvents(...)` formats and prints a simple table of events.

## Rate limiting & API notes

- The GitHub REST API is rate-limited for unauthenticated requests (60 requests per hour per IP). If you need more requests consider using authenticated requests with a token (not implemented in this project).
- Only public events are returned by the `/users/{username}/events` endpoint.

## Errors & troubleshooting

- HTTP errors (non-200 responses) are printed to stderr. Ensure network access and that the username exists.
- If you see parsing errors, it may be due to an unexpected change in the API response. The project uses Gson to map only the fields it needs.

## Development

Open the project in your IDE and run or debug `com.github.cli.Main` with program arguments, for example:

Run configuration program arguments:

```
-u octocat
```

## Suggestions / Next steps

- Add optional GitHub token support for authenticated requests and higher rate limits.
- Support filtering by event type or limiting number of events displayed.
- Add unit tests around `JsonParser` and `Utils.displayEvents`.

## License

This repository does not contain an explicit license file. Add a LICENSE file if you intend to permit reuse.

## Contact

Open an issue or pull request on the repository for bugs, suggestions or improvements.
