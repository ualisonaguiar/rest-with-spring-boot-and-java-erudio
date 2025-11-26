package br.github.model;

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
@EqualsAndHashCode
@RequiredArgsConstructor
@Table(name = "tb_pessoa")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "primeiro_nome", nullable = false, length = 80)
    private String firstName;

    @Column(name = "ultimo_nome", nullable = false, length = 80)
    private String lastName;

    @Column(name = "endereco", nullable = false, length = 100)
    private String address;

    @Column(name = "genero", nullable = false, length = 12)
    private String gender;

    @Column(name = "ativo", nullable = false)
    private Boolean enable;

    @Column(name = "situacao", nullable = false)
    private Boolean situacao;
}
