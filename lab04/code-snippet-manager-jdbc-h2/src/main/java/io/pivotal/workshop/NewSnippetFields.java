package io.pivotal.workshop;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class NewSnippetFields {

    public final String title;
    public final String code;

    private NewSnippetFields() {
        this(null, null);
    }
}
