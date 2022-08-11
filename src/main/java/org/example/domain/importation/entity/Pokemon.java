package org.example.domain.importation.entity;
;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "pokemon")
@Setter
@Getter
public class Pokemon {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dex_number")
    private int dexNumber;

    @Column(name = "name")
    private String name;

    @Column(name = "legendary")
    private boolean legendary;

    @Column(name = "generation")
    private int generation;

    @ManyToOne
    @JoinColumn(name = "type_one", referencedColumnName = "id")
    private Type typeOne;

    @ManyToOne
    @JoinColumn(name = "type_two", referencedColumnName = "id")
    private Type typeTwo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "base_stats_id", referencedColumnName = "id")
    private BaseStats baseStats;
}
