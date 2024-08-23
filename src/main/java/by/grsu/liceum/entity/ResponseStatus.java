package by.grsu.liceum.entity;

import by.grsu.liceum.entity.enums.ResponseStatusConstant;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "response_statuses")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private ResponseStatusConstant name;
    @Column(name = "description")
    private String description;
}
