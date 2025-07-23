package com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.controller;

import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.entity.BusRoute;
import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.dto.ApiResponse;
import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.service.BusRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bus-route")
public class BusRouteController {

    @Autowired
    private BusRouteService busRouteService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BusRoute>>> getAllRoutes() {
        List<BusRoute> routes = busRouteService.getAllRoutes();
        return ResponseEntity.ok(new ApiResponse<>(true, "List of bus routes", routes, HttpStatus.OK));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<BusRoute>> addRoute(@RequestBody BusRoute route) {
        BusRoute newRoute = busRouteService.addRoute(route);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse<>(true, "Bus route added successfully", newRoute, HttpStatus.CREATED));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<BusRoute>> updateRoute(@PathVariable int id, @RequestBody BusRoute route) {
        BusRoute updatedRoute = busRouteService.updateRoute(id, route);
        return ResponseEntity.ok(new ApiResponse<>(true, "Bus route updated successfully", updatedRoute, HttpStatus.OK));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteRoute(@PathVariable int id) {
        busRouteService.deleteRoute(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Bus route deleted successfully", null, HttpStatus.OK));
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<BusRoute>>> searchRoutes(
            @RequestParam(required = false) String startPoint,
            @RequestParam(required = false) String endPoint) {
        List<BusRoute> routes = busRouteService.searchRoutes(startPoint, endPoint);
        return ResponseEntity.ok(new ApiResponse<>(true, "Search results", routes, HttpStatus.OK));
    }
}