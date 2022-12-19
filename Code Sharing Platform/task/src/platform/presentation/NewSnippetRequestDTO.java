package platform.presentation;

import lombok.Data;

@Data
public class NewSnippetRequestDTO {
    public String code;
    public Integer time;
    public Integer views;
}
