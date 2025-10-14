package br.github.controllers.docs;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import br.github.data.dto.v1.BookDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

public interface BookControllerDocs {

        @Operation(summary = "Find All Book", tags = { "Book" }, description = "Find All Book", responses = {
                        @ApiResponse(description = "Success", responseCode = "204", content = {
                                        @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, array = @ArraySchema(schema = @Schema(implementation = BookDTO.class)))
                        }),
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
        public List<BookDTO> findAll();

        @Operation(summary = "Find By Book", tags = { "Book" }, description = "Find By Book", responses = {
                        @ApiResponse(description = "Success", responseCode = "204", content = @Content(schema = @Schema(implementation = BookDTO.class))),
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
        public BookDTO findById(@PathVariable("id") Long id);

        @Operation(summary = "Create By Book", tags = { "Book" }, description = "Create By Book", responses = {
                        @ApiResponse(description = "Success", responseCode = "204", content = @Content(schema = @Schema(implementation = BookDTO.class))),
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
        public BookDTO create(@RequestBody BookDTO book);

        @Operation(summary = "Update By Book", tags = { "Book" }, description = "Update By Book", responses = {
                        @ApiResponse(description = "Success", responseCode = "204", content = @Content(schema = @Schema(implementation = BookDTO.class))),
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
        public BookDTO update(@RequestBody BookDTO BookDTO, @PathVariable("id") final Long id);

        @Operation(summary = "Delete By Book", tags = { "Book" }, description = "Delete By Book", responses = {
                        @ApiResponse(description = "Success", responseCode = "204", content = @Content(schema = @Schema(implementation = BookDTO.class))),
                        @ApiResponse(description = "No Content", responseCode = "204", content = @Content),
                        @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                        @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                        @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                        @ApiResponse(description = "Internal Server Error", responseCode = "500", content = @Content),
        })
        public ResponseEntity<?> delete(@PathVariable("id") Long id);

}
