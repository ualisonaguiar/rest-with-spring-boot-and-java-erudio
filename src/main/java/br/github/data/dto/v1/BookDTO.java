package br.github.data.dto.v1;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class BookDTO extends RepresentationModel<BookDTO> {

    private Long id;
    private String author;
    private String title;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date launchDate;

    private BigDecimal price;

}
