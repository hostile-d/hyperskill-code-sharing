package platform.presentation;

import platform.businesslayer.Snippet;
import platform.businesslayer.SnippetService;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class SnippetController {
    @Autowired
    SnippetService snippetService;

    @PostMapping("/api/code/new")
    public ResponseEntity<NewSnippetResponseDTO> addSnippet(@RequestBody NewSnippetRequestDTO snippetDTO) {
        var id = snippetService.store(snippetDTO);
        return new ResponseEntity<NewSnippetResponseDTO>(new NewSnippetResponseDTO(id), HttpStatus.OK);
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<Snippet> getCodeData(@PathVariable @NotNull Integer id) {
        return new ResponseEntity<Snippet>(snippetService.findById(id).orElse(null), HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<Collection<Snippet>> getLatest() {
        return new ResponseEntity<Collection<Snippet>>(snippetService.getLatest(), HttpStatus.OK);
    }

    @GetMapping("/code/{id}")
    public String getCodeView(@PathVariable @NotNull Integer id, Model model) {
        model.addAttribute("snippet", snippetService.findById(id).orElse(null));
        return "snippet";
    }

    @GetMapping("/code/latest")
    public String getCodeView(Model model) {
        model.addAttribute("snippets", snippetService.getLatest());
        return "latestSnippets";
    }

    @GetMapping("/code/new")
    public String addSnippetView(Model model) {
        return "newSnippetPage";
    }
}
