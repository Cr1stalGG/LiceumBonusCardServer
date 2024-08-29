package by.grsu.liceum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "institutions")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Institution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "city")
    private String city;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "institution",
            orphanRemoval = true
    )
    private List<Account> accounts;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "institution",
            orphanRemoval = true
    )
    private List<ActivityType> activityTypes;//todo remove unique from name

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "institution",
            orphanRemoval = true
    )
    private List<Bonus> bonuses;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "institution",
            orphanRemoval = true
    )
    private List<Group> groups;

    //todo add custom roles
}
