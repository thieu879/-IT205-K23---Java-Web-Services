package com.data.ss7.controller;

import com.data.ss7.model.Harvest;
import com.data.ss7.model.dto.DataResponse;
import com.data.ss7.service.HarvestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping("/harvests")
public class HarvestController {

    @Autowired
    private HarvestService harvestService;

    @GetMapping
    public ResponseEntity<DataResponse<List<Harvest>>> getHarvests() {
        List<Harvest> harvests = harvestService.getAllHarvests();
        return new ResponseEntity<>(new DataResponse<>(harvests, HttpStatus.OK), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<DataResponse<Harvest>> addHarvest(@Valid @RequestBody Harvest harvest) {
        Harvest savedHarvest = harvestService.addHarvest(harvest);
        return new ResponseEntity<>(new DataResponse<>(savedHarvest, HttpStatus.CREATED), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<?>> getHarvestById(@PathVariable Long id) {
        try {
            Harvest harvest = harvestService.getHarvestById(id);
            return new ResponseEntity<>(new DataResponse<>(harvest, HttpStatus.OK), HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(new DataResponse<>(e.getMessage(), HttpStatus.NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }
}