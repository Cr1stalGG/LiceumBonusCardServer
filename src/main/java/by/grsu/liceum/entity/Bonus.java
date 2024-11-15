package by.grsu.liceum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bonuses")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Bonus {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "count")
    private int count;
    @Column(name = "time_of_end")
    private Date timeOfEnd;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "bonus",
            orphanRemoval = true
    )
    private List<Ticket> tickets;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;
}
