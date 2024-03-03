package com.example.anihubbackend;

import com.example.anihubbackend.models.ExceptionResponse;
import com.example.anihubbackend.services.AnimeFetcherService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.IOException;
import java.util.Date;

@RestController
@Slf4j
@CrossOrigin
public class MainController {
    @Autowired
    private AnimeFetcherService animeFetcherService;

    @GetMapping(path="/all")
    public ResponseEntity<Object> getAll() throws IOException {
        return ResponseEntity.ok()
                .body(animeFetcherService.getAllAnime());
    }

    @GetMapping(path="/all/{id}")
    public ResponseEntity<Object> getAll(@PathVariable String id) throws IOException {
        return ResponseEntity.ok()
                .body(animeFetcherService.getAllAnime(id));
    }

    @GetMapping(path="/search/{text}")
    public ResponseEntity<Object> searchByText(@PathVariable String text) throws IOException {
        return ResponseEntity.ok()
                .body(animeFetcherService.searchAnime(text));
    }

    @GetMapping(path="/search/{text}/{page}")
    public ResponseEntity<Object> searchByText(@PathVariable String text,
                                               @PathVariable String page) throws IOException {
        return ResponseEntity.ok()
                .body(animeFetcherService.searchAnime(text, page));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exceptionHandler(Exception ex, WebRequest req) {
        ex.printStackTrace();

        return ResponseEntity.internalServerError()
                .body(new ExceptionResponse(
                ex.getMessage(),
                new Date(),
                ex.getClass(),
                req.getDescription(false),
                ex.toString()
        ));
    }
}
