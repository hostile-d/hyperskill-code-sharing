package presentation;

import lombok.Data;

@Data
public class NewSnippetResponseDTO {
    public Integer id;
    public NewSnippetResponseDTO(Integer id) {
        this.id = id;
    }

    public String getId() {
        return String.valueOf(id);
    }
}
