package platform.presentation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class SnippetResponseDTO {
    public String code;

    public String date;

    public Integer time;

    public Integer views;

    @JsonIgnore
    public Boolean viewsRestriction;

    @JsonIgnore
    public Boolean timeRestriction;
}
