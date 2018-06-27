package io.pivotal.workshop.domain;

import java.util.Date;

public class SnippetNotifier {

    String title;
    Date created;

    public SnippetNotifier() {}

    public String getTitle() {
        return title;
    }

    public Date getCreated() {
        return created;
    }
}
