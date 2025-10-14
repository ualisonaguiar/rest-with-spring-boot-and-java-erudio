package br.github.model;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_livro")
@EqualsAndHashCode
@RequiredArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "autor", nullable = false, length = 150)
    private String author;

    @Column(name = "titulo", nullable = false, length = 100)
    private String title;

    @Column(name = "data_lancamento", nullable = false)
    private Date launchDate;

    @Column(name = "preco", nullable = false, precision = 65, scale = 2)
    private BigDecimal price;
}
