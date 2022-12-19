package platform.businesslayer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
@Entity
@Table(name = "snippets")
public class Snippet implements Comparable<Snippet> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Column
    private Integer id;

    @Column
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
        name = "UUID",
        strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID uuid;

    @Column
    private String code;

    @Column
    private LocalDateTime date;

    @Column
    private Integer time;

    @Column
    private Integer views;

    @JsonIgnore
    @Column
    private Boolean viewsRestriction;

    @JsonIgnore
    @Column
    private Boolean timeRestriction;

    @Override
    public int compareTo(Snippet snippet) {
        return this.date.compareTo(snippet.date);
    }

    public UUID getUuid() {
        if (uuid == null) {
            return UUID.randomUUID();
        }
        return uuid;
    }
}
