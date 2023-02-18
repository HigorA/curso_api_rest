package br.com.curso.controllers;

import br.com.curso.data.vo.v1.BooksVO;
import br.com.curso.services.BooksService;
import br.com.curso.util.MediaType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books/v1")
@Tag(name = "Books", description = "Endpoints to manage books.")
public class BooksController {

    @Autowired
    private BooksService service;

    @GetMapping(produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML})
    @Operation(summary = "Finds all Books.", description = "Finds all Books.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = BooksVO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<List<BooksVO>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findAll());
    }

    @GetMapping(
            path = "/{id}",
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(summary = "Finds a Books.", description = "Finds a Books.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BooksVO.class))
                    ),
                    @ApiResponse(description = "No Content.", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<BooksVO> findById(@PathVariable("id") Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(summary = "Add a new Book.", description = "Add a new Book.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Sucess!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BooksVO.class))
                    ),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<BooksVO> create(@RequestBody BooksVO booksVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(booksVO));
    }

    @PutMapping(
            consumes = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML},
            produces = {MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YML}
    )
    @Operation(summary = "Updates a Book.", description = "Updates a Book.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "Updated!", responseCode = "200",
                            content = @Content(schema = @Schema(implementation = BooksVO.class))
                    ),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<BooksVO> update(@RequestBody BooksVO booksVO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.update(booksVO));
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Deletes a Book.", description = "Deletes a Book.",
            tags = {"Books"},
            responses = {
                    @ApiResponse(description = "No Content!", responseCode = "204", content = @Content),
                    @ApiResponse(description = "Bad Request.", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized.", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found.", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error.", responseCode = "500", content = @Content),
            })
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
