package businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Snippet implements Comparable<Snippet> {
    @JsonIgnore
    private Integer id;

    private String code;

    private LocalDateTime date;

    @Override
    public int compareTo(Snippet snippet) {
        return this.date.compareTo(snippet.date);
    }

    public Snippet(Integer id, String code, LocalDateTime date) {
        this.id = id;
        this.code = code;
        this.date = date;
    }

    public Snippet(){}

    public String getDate() {
        if (date != null) {
            return date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        return null;
    }
}
