package io.pivotal.workshop;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<SnippetInfo> snippets() {
        return snippetRepository.findAll()
                .stream()
                .map(snippetPresenter::present)
                .collect(toList());
    }

    @GetMapping("/{id}")
    public SnippetInfo snippet(@PathVariable("id") String id) {
        SnippetRecord record = snippetRepository.findById(id);
        return snippetPresenter.present(record);
    }

    @PostMapping
    public ResponseEntity<SnippetInfo> add(@RequestBody NewSnippetFields newSnippetFields) {
        SnippetRecord savedSnippetRecord = snippetRepository.save(newSnippetFields);
        SnippetInfo savedSnippetInfo = snippetPresenter.present(savedSnippetRecord);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(buildSnippetUri(savedSnippetInfo));

        return new ResponseEntity<SnippetInfo>(savedSnippetInfo, httpHeaders, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SnippetInfo> update(@PathVariable("id") String id, @RequestBody NewSnippetFields newSnippetFields) {
        SnippetRecord snippetRecord = snippetRepository.update(id, newSnippetFields);
        SnippetInfo snippetInfo = snippetPresenter.present(snippetRecord);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(buildSnippetUri(snippetInfo));

        return new ResponseEntity<SnippetInfo>(snippetInfo, httpHeaders, HttpStatus.OK);
    }

    private URI buildSnippetUri(SnippetInfo snippet) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest().path("/" + snippet.id)
                .buildAndExpand().toUri();
    }
}
