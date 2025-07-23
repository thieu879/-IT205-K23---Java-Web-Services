package com.data.ptit_hnks23b_ptithnk23036_nguyengiathieu_001.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusDto {
    private String busName;
    private String registrationNumber;
    private int totalSeats;
    private MultipartFile imageBus;
    private boolean status;
}