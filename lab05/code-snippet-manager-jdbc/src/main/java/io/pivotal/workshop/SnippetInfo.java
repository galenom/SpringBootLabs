package io.pivotal.workshop;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SnippetInfo {

    public final String id;
    public final String title;
    public final String code;
    public final String created;
    public final String modified;

}
