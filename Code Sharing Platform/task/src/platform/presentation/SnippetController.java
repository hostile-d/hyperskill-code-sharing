package platform.presentation;

import platform.SnippetNotFoundException;
import platform.businesslayer.Snippet;
import platform.businesslayer.SnippetService;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.Collectors;

@Controller
public class SnippetController {
    @Autowired
    SnippetService snippetService;

    public final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @PostMapping("/api/code/new")
    public ResponseEntity<NewSnippetResponseDTO> addSnippet(@RequestBody NewSnippetRequestDTO snippetDTO) {
        var uuid = snippetService.store(snippetDTO);
        return new ResponseEntity<NewSnippetResponseDTO>(new NewSnippetResponseDTO(uuid), HttpStatus.OK);
    }

    @GetMapping("/api/code/{uuid}")
    public ResponseEntity getCodeData(@PathVariable @NotNull UUID uuid) {
        var responseDTO = getResponseModel(snippetService.findByUUID(uuid));
        if (responseDTO == null) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SnippetResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/api/code/latest")
    public ResponseEntity<Collection<SnippetResponseDTO>> getLatest() {
        var response  = snippetService.getLatest().stream().map(this::getResponseModel).collect(Collectors.toList());
        return new ResponseEntity<Collection<SnippetResponseDTO>>(response, HttpStatus.OK);
    }

    @GetMapping("/code/{uuid}")
    public String getCodeView(@PathVariable @NotNull UUID uuid, Model model) {
        var responseDTO = getResponseModel(snippetService.findByUUID(uuid));
        if (responseDTO == null) {
            throw new SnippetNotFoundException();
        }
        model.addAttribute("snippet", responseDTO);
        return "snippet";
    }

    @GetMapping("/code/latest")
    public String getCodeView(Model model) {
        var responseDTO = snippetService.getLatest().stream().map(this::getResponseModel).collect(Collectors.toList());
        if (responseDTO.size() == 0) {
            throw new SnippetNotFoundException();
        }
        model.addAttribute("snippets", responseDTO);
        return "latestSnippets";
    }

    @GetMapping("/code/new")
    public String addSnippetView(Model model) {
        return "newSnippetPage";
    }

    private SnippetResponseDTO getResponseModel(Snippet snippet) {
        if (snippet == null) {
            return null;
        }
        var response = new SnippetResponseDTO();
        response.setCode(snippet.getCode());
        response.setDate(snippet.getDate().format(dateTimeFormat));
        response.setTime(snippet.getTime());
        response.setViews(snippet.getViews());
        response.setTimeRestriction(snippet.getTimeRestriction());
        response.setViewsRestriction(snippet.getViewsRestriction());
        return response;
    }
}
