package com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.controller;

import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.dto.BusDto;
import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.entity.Bus;
import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.service.BusService;
import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.dto.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/bus")
public class BusController {

    @Autowired
    private BusService busService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Bus>>> getAllBuses() {
        List<Bus> buses = busService.getAllBuses();
        return ResponseEntity.ok(new ApiResponse<>(true, "List of buses", buses, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<Bus>> addBus(@ModelAttribute BusDto busDto) throws IOException {
        Bus newBus = busService.addBus(busDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Bus added successfully", newBus, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Bus>> updateBus(@PathVariable int id, @RequestBody Bus bus) {
        Bus updatedBus = busService.updateBus(id, bus);
        return ResponseEntity.ok(new ApiResponse<>(true, "Bus updated successfully", updatedBus, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBus(@PathVariable int id) {
        busService.deleteBus(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Bus deleted successfully", null, HttpStatus.OK));
    }
}