package com.data.ss7.service;

import com.data.ss7.model.Worker;

import java.util.List;

public interface WorkerService {
    List<Worker> getAllWorkers();
    Worker getWorkerById(Long id);
    Worker addWorker(Worker worker);
    Worker updateWorker(Long id, Worker worker);
    void deleteWorker(Long id);
}
