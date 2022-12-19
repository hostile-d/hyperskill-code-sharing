package platform.presentation;

import lombok.Data;

@Data
public class NewSnippetResponseDTO {
    public String id;
    public NewSnippetResponseDTO(String uuid) {
        this.id = uuid;
    }
}
