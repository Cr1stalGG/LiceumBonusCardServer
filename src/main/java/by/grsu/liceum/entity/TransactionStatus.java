package by.grsu.liceum.entity;

import by.grsu.liceum.entity.enums.StatusConstant;
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
@Table(name = "statuses")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private StatusConstant name;
    @Column(name = "description")
    private String description;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "transactionStatus",
            orphanRemoval = true
    )
    private List<Transaction> transactions;
}