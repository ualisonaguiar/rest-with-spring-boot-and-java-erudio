package br.github.data.dto.v1;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PersonDTO{

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
}
