package presentation;

import businesslayer.Snippet;
import businesslayer.SnippetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileNotFoundException;
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

    @RequestMapping(
            value = "/code",
            method = RequestMethod.GET,
            produces = "text/html"
    )
    public String getCodeView() {
        var snippet = snippetService.getById(0);
        StringBuilder result = new StringBuilder();

        try {
            File myObj = new File("./src/presentation/snippetResponse.html");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (Objects.equals(data, "@snippet")) {
                    result.append("\n").append(snippet.getCode()).append("\n");
                    continue;
                }
                result.append(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return result.toString();
    }
}
