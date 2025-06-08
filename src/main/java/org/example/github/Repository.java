package org.example.github;

import java.time.OffsetDateTime;

public class Repository {
    private String name;
    private String updated_at;
    private int watchers;
    private int open_issues_count;

    public Repository(String name, String updatedAt, int watchers, int open_issues_count) {
        this.name = name;
        this.updated_at = updatedAt;
        this.watchers = watchers;
        this.open_issues_count = open_issues_count;
    }

    public String getName() { return name; }
    public OffsetDateTime getUpdatedAt() {
        if (updated_at == null || updated_at.isEmpty()) {
            return null;
        }
        return OffsetDateTime.parse(updated_at.replace("Z", "+00:00"));
    }
    public int getWatchers() { return watchers; }
    public int getOpen_issues_count() { return open_issues_count; }
}
