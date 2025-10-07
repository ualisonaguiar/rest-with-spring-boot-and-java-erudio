package br.github.data.dto.v2;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonPropertyOrder({"id", "firstName", "lastName", "birthDay", "gender"})
public class PersonDTOV2 {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String gender;
    private Date birthDay;
}
