package br.github.data.dto.v2;

import java.util.Date;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class PersonDTOV2 {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private Date birthDay;
}
