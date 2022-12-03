package presentation;

import businesslayer.NewSnippetDTO;
import businesslayer.Snippet;
import businesslayer.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

@RestController
public class SnippetController {
    @Autowired
    SnippetService snippetService;

    @GetMapping("/api/code")
    public ResponseEntity<Snippet> getCodeData() {
        return new ResponseEntity<Snippet>(snippetService.getById(0), HttpStatus.OK);
    }

    @GetMapping(value = "/code", produces = MediaType.TEXT_HTML_VALUE)
    public String getCodeView() {
        var snippet = snippetService.getById(0);
        var replacements = new HashMap<String, String>();
        replacements.put("@snippet", snippet.getCode());
        replacements.put("@date", snippet.getDate().toString());

        return getView("./src/public/snippetResponse.html", replacements);
    }

    @GetMapping(value = "/code/new", produces = MediaType.TEXT_HTML_VALUE)
    public String updateSnippetView() {
        return getView("./src/public/newSnippetPage.html", new HashMap<String, String>());
    }

    @PostMapping("/api/code/new")
    public ResponseEntity<Snippet> updateSnippet(@RequestBody NewSnippetDTO snippetDTO) {
        snippetService.update(snippetDTO);
        return new ResponseEntity<Snippet>(new Snippet(), HttpStatus.OK);
    }

    private String getView(String path, HashMap<String, String> replacements) {
        StringBuilder result = new StringBuilder();

        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (replacements.isEmpty()) {
                    result.append(data);
                    continue;
                }

                var replacement = replacements.get(data);
                if (Objects.equals(replacement, null)) {
                    result.append(data);
                    continue;
                }
                result.append("\n").append(replacement).append("\n");
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result.toString();
    }
}
