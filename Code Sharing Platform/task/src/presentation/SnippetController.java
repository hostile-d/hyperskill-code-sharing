package presentation;

import businesslayer.Snippet;
import businesslayer.SnippetService;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class SnippetController {
    @Autowired
    SnippetService snippetService;
    private final Integer LATEST_AMOUNT = 10;

    @PostMapping("/api/code/new")
    public ResponseEntity<NewSnippetResponseDTO> addSnippet(@RequestBody NewSnippetRequestDTO snippetDTO) {
        var id = snippetService.store(snippetDTO);
        return new ResponseEntity<NewSnippetResponseDTO>(new NewSnippetResponseDTO(id), HttpStatus.OK);
    }

    @GetMapping("/api/code/{id}")
    public ResponseEntity<Snippet> getCodeData(@PathVariable @NotNull Integer id) {
        return new ResponseEntity<Snippet>(snippetService.getById(id), HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<List<Snippet>> getLatest() {
        return new ResponseEntity<List<Snippet>>(snippetService.getLatest(LATEST_AMOUNT), HttpStatus.OK);
    }

    @GetMapping("/code/{id}")
    public String getCodeView(@PathVariable @NotNull Integer id, Model model) {
        var snippet = snippetService.getById(id);
        model.addAttribute("snippet", snippet);
        return "snippet";
    }

    @GetMapping("/code/latest")
    public String getCodeView(Model model) {
        model.addAttribute("snippets", snippetService.getLatest(LATEST_AMOUNT));
        return "latestSnippets";
    }

    @GetMapping("/code/new")
    public String addSnippetView(Model model) {
        return "newSnippetPage";
    }
}
