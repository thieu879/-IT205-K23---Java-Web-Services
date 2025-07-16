package com.data.ss7.controller;

import com.data.ss7.model.dto.DataResponse;
import com.data.ss7.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class StatisticalController {

    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/remaining-seeds")
    public ResponseEntity<DataResponse<Integer>> getRemainingSeeds() {
        int remainingSeeds = statisticalService.countRemainingSeeds();
        return new ResponseEntity<>(new DataResponse<>(remainingSeeds, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/harvest-money")
    public ResponseEntity<DataResponse<Double>> getHarvestMoneyThisMonth() {
        double harvestMoney = statisticalService.totalHarvestMoneyThisMonth();
        return new ResponseEntity<>(new DataResponse<>(harvestMoney, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/payment-slips")
    public ResponseEntity<DataResponse<Double>> getPaymentSlipsThisMonth() {
        double paymentSlipsMoney = statisticalService.totalPaymentSlipsThisMonth();
        return new ResponseEntity<>(new DataResponse<>(paymentSlipsMoney, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/profit-loss")
    public ResponseEntity<DataResponse<Map<String, Double>>> getProfitLossOverYear() {
        Map<String, Double> profitLoss = statisticalService.profitLossOverYear();
        return new ResponseEntity<>(new DataResponse<>(profitLoss, HttpStatus.OK), HttpStatus.OK);
    }

    @GetMapping("/worker-salary")
    public ResponseEntity<DataResponse<Double>> getWorkerSalaryThisMonth() {
        double workerSalary = statisticalService.totalWorkerSalaryThisMonth();
        return new ResponseEntity<>(new DataResponse<>(workerSalary, HttpStatus.OK), HttpStatus.OK);
    }
}
