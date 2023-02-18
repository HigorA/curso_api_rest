package br.com.curso.controllers;

import br.com.curso.data.vo.v1.PersonVO;
import br.com.curso.services.PersonService;
import br.com.curso.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person/v1")
@Tag(name = "People", description = "Endpoints to manage people.")
public class PersonController {

    @Autowired
    PersonService service;

    @GetMapping(value = "/{id}",
    produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds a Person.", description = "Finds a Person.",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "No Content.", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public PersonVO findById(@PathVariable(value = "id") Long id) {
        return service.findById(id);
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all people.", description = "Finds all people.",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public List<PersonVO> findAll() {
        return service.findAll();
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Add a new Person.", description = "Add a new Person.",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public PersonVO create(@RequestBody PersonVO person) {
        return service.create(person);
    }

    @PutMapping(consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Updates a Person.", description = "Updates a Person.",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "Updated!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = PersonVO.class))
                    ),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public PersonVO update(@RequestBody PersonVO person) {
        return service.update(person);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Deletes a Person.", description = "Deletes a Person.",
            tags = {"People"},
            responses = {
                    @ApiResponse(description = "No Content!", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
