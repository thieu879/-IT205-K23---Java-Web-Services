package com.data.ss14.service;

import com.data.ss14.model.dto.request.TicketRequestDTO;
import com.data.ss14.model.dto.response.TicketResponseDTO;

import java.util.List;

public interface TicketService {
    TicketResponseDTO bookTicket(TicketRequestDTO dto, String username);
    List<TicketResponseDTO> getMyTickets(String username);
    List<TicketResponseDTO> getAllTickets();
}
