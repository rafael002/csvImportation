package org.example.domain.importation.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "base_stats")
public class BaseStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "hp")
    private int hp;

    @Column(name = "atk")
    private int atk;

    @Column(name = "def")
    private int def;

    @Column(name = "sp_atk")
    private int spAtk;

    @Column(name = "sp_def")
    private int spDef;

    @Column(name = "speed")
    private int speed;
}
