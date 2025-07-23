package com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.service;

import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.dto.BusDto;
import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.entity.Bus;
import com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.repository.BusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@Transactional
public class BusService {
    @Autowired
    private BusRepository busRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    public List<Bus> getAllBuses() {
        return busRepository.findAll();
    }

    public Bus addBus(BusDto busDto) throws IOException {
        String img = null;
        if (busDto.getImageBus() != null && !busDto.getImageBus().isEmpty()) {
            img = cloudinaryService.uploadFile(busDto.getImageBus());
        }
        if (busRepository.existsByBusName(busDto.getBusName())) {
            throw new RuntimeException("Bus name already exists");
        }
        Bus bus = new Bus();
        bus.setBusName(busDto.getBusName());
        bus.setRegistrationNumber(busDto.getRegistrationNumber());
        bus.setTotalSeats(busDto.getTotalSeats());
        bus.setStatus(busDto.isStatus());
        bus.setImageBus(img);
        return busRepository.save(bus);
    }

    public Bus updateBus(int id, Bus bus) {
        Bus existingBus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        if (!existingBus.getBusName().equals(bus.getBusName()) &&
                busRepository.existsByBusName(bus.getBusName())) {
            throw new RuntimeException("Bus name already exists");
        }
        existingBus.setBusName(bus.getBusName());
        existingBus.setRegistrationNumber(bus.getRegistrationNumber());
        existingBus.setTotalSeats(bus.getTotalSeats());
        existingBus.setStatus(bus.isStatus());
        existingBus.setImageBus(bus.getImageBus());
        return busRepository.save(existingBus);
    }

    public void deleteBus(int id) {
        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        bus.setStatus(false);
        busRepository.save(bus);
    }
}