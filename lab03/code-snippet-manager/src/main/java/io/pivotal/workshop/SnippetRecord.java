package io.pivotal.workshop;

import java.time.LocalDate;

public class SnippetRecord {

    public final String id;
    public String title;
    public String code;
    public final LocalDate created;
    public LocalDate modified;

    public SnippetRecord(String id, String title, String code, LocalDate created, LocalDate modified) {
        this.id = id;
        this.title = title;
        this.code = code;
        this.created = created;
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getCreated() {
        return created;
    }

    public LocalDate getModified() {
        return modified;
    }

    public void setModified(LocalDate modified) {
        this.modified = modified;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SnippetRecord that = (SnippetRecord) o;

        return id != null ? id.equals(that.id) : that.id == null;
    }
}
