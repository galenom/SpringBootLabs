package io.pivotal.workshop;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/snippets")
public class SnippetController {
    private final SnippetRepository snippetRepository;
    private final SnippetPresenter snippetPresenter;

    public SnippetController(SnippetRepository snippetRepository, SnippetPresenter snippetPresenter) {
        this.snippetRepository = snippetRepository;
        this.snippetPresenter = snippetPresenter;
    }

    @GetMapping
    public List<SnippetInfo> snippets(@RequestParam(value = "start", required = false) String start, @RequestParam(value = "end", required = false) String end) {
        List<SnippetRecord> records;
        if (start == null || end == null) {
            records = snippetRepository.findAll();
        } else {
            records = snippetRepository.findByDateRange(start, end);
        }
        return records
                .stream()
                .map(snippetPresenter::present)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public SnippetInfo snippet(@PathVariable("id") String id) {
        SnippetRecord record = snippetRepository.findOne(id);
        return snippetPresenter.present(record);
    }

    @PostMapping
    public ResponseEntity<SnippetInfo> add(@RequestBody NewSnippetFields newSnippetFields) {
        SnippetRecord newRecord = snippetRepository.save(newSnippetFields);
        SnippetInfo newInfo = snippetPresenter.present(newRecord);

        HttpHeaders httpHeaders = new HttpHeaders();

        httpHeaders.setLocation(buildSnippetUri(newInfo));

        return new ResponseEntity<>(newInfo, httpHeaders, HttpStatus.CREATED);
    }

    private URI buildSnippetUri(SnippetInfo snippetInfo) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + snippetInfo.id)
                .buildAndExpand().toUri();
    }
}
