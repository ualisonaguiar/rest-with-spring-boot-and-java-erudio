package br.github.data.dto.v1;

import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.github.serializer.GenderSerializer;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@JsonFilter("PersonFilter")
public class PersonDTO extends RepresentationModel<PersonDTO> {

    private Long id;
    private String firstName;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String lastName;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String phoneNumber;

    private String address;

    @JsonSerialize(using = GenderSerializer.class)
    private String gender;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private Date birthDay;

    private String sensitiveData;
}
