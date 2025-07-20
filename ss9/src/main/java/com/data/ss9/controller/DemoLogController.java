package com.data.ss9.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo-log")
public class DemoLogController {
    private static final Logger logger = LoggerFactory.getLogger(DemoLogController.class);
    @GetMapping("/trace")
    public String trace() {
        logger.trace("Đã ghi log trace");
        return "TRACE log message sent";
    }
    @GetMapping("/debug")
    public String debug() {
        logger.debug("Đã ghi log debug");
        return "DEBUG log message sent";
    }
    @GetMapping("/info")
    public String info() {
        logger.info("Đã ghi log info");
        return "INFO log message sent";
    }
    @GetMapping("/warning")
    public String warn() {
        logger.warn("Đã ghi log warning");
        return "WARNING log message sent";
    }
    @GetMapping("/error")
    public String error() {
        logger.error("Đã ghi log error");
        return "ERROR log message sent";
    }
}
