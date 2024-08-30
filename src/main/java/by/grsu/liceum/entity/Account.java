package by.grsu.liceum.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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

@NamedEntityGraph(
    name = "account-with-roles",
    attributeNodes = {
            @NamedAttributeNode("roles")
    }
)
@Entity
@Table(name="accounts")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {
    @Id
    @UuidGenerator(style = UuidGenerator.Style.AUTO)
    private UUID id;
    @Column(name = "login")
    private String login;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "year_of_start_of_studying")
    private Date yearOfStartOfStudying;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private Card card;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "institution_id")
    private Institution institution;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "account",
            orphanRemoval = true
    )
    private List<Response> responses;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "account",
            orphanRemoval = true
    )
    private List<Ticket> tickets;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "admin",
            orphanRemoval = true
    )
    private List<Group> ownedGroups;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "accounts_groups",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Group> otherGroups;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "accounts_roles",
            joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "account",
            orphanRemoval = true
    )
    private List<SolvedActivity> solvedActivities;
}
