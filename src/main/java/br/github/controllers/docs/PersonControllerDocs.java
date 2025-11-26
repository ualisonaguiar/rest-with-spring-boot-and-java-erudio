package br.github.controllers.docs;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.github.data.dto.v1.PersonDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface PersonControllerDocs {

    @Operation(summary = "Find All People", tags = { "People" }, description = "Find All People", responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = {
                    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = PersonDTO.class)))
            }),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    })
    public List<PersonDTO> findAll();

    @Operation(summary = "Find By Person", tags = { "People" }, description = "Find By Person", responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    })
    public PersonDTO findById(@PathVariable("id") Long id);

    @Operation(summary = "Create By Person", tags = { "People" }, description = "Create By Person", responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    })
    public PersonDTO create(@RequestBody PersonDTO person);

    @Operation(summary = "Update By Person", tags = { "People" }, description = "Update By Person", responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    })
    public PersonDTO update(@RequestBody PersonDTO person);

    @Operation(summary = "Delete By Person", tags = { "People" }, description = "Delete By Person", responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    })
    public ResponseEntity<?> delete(@PathVariable("id") Long id);

    @Operation(summary = "Disable a Person", tags = { "People" }, description = "Find By Person", responses = {
            @ApiResponse(description = "Success", responseCode = "204", content = @Content(schema = @Schema(implementation = PersonDTO.class))),
            @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
            @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
            @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
            @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
            @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
    })
    public PersonDTO disablePerson(@PathVariable("id") Long id);    

}
