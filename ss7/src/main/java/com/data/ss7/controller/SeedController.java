package com.data.ss7.controller;

import com.data.ss7.model.Seed;
import com.data.ss7.model.dto.DataResponse;
import com.data.ss7.service.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/seeds")
public class SeedController {

    @Autowired
    private SeedService seedService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Seed>>> getSeeds() {
        List<Seed> Seeds = seedService.getAllSeeds();
        return new ResponseEntity<>(new DataResponse<>(Seeds, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Seed>> addSeed( @RequestBody Seed seed) {
        Seed savedSeed = seedService.addSeed(seed);
        return new ResponseEntity<>(new DataResponse<>(savedSeed, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<Seed>> updateSeed(@PathVariable Long id, @Valid @RequestBody Seed seed) {
        Seed updatedSeed = seedService.updateSeed(id, seed);
        return new ResponseEntity<>(new DataResponse<>(updatedSeed, HttpStatus.OK), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DataResponse<String>> deleteSeed(@PathVariable Long id) {
        try {
            seedService.deleteSeed(id);
            return new ResponseEntity<>(new DataResponse<>("Xóa giống thành công", HttpStatus.NO_CONTENT), HttpStatus.NO_CONTENT);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new DataResponse<>(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }
}
