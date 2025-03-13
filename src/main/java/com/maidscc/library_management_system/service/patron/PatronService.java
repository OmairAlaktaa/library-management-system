package com.maidscc.library_management_system.service.patron;

import com.maidscc.library_management_system.dto.patron.*;
import com.maidscc.library_management_system.model.Patron;
import com.maidscc.library_management_system.repository.patron.PatronRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatronService {
    private final PatronRepository patronRepository;

    public PatronService(PatronRepository patronRepository) {
        this.patronRepository = patronRepository;
    }

    public List<PatronResponseDTO> getAllPatrons() {
        return patronRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    public PatronResponseDTO getPatronById(Integer patronId) {
        return patronRepository.findById(patronId).map(this::mapToDTO).orElseThrow(() -> new RuntimeException("Patron not found"));
    }

    public PatronResponseDTO createPatron(PatronRequestDTO patronRequest) {
        Patron patron = new Patron();
        patron.setFirstName(patronRequest.getFirstName());
        patron.setLastName(patronRequest.getLastName());
        patron.setEmail(patronRequest.getEmail());
        patron.setPhoneNumber(patronRequest.getPhoneNumber());

        Patron savedPatron = patronRepository.save(patron);
        return mapToDTO(savedPatron);
    }

    public PatronResponseDTO updatePatron(Integer patronId, PatronRequestDTO patronRequest) {
        Patron patron = patronRepository.findById(patronId).orElseThrow(() -> new RuntimeException("Patron not found"));
        patron.setFirstName(patronRequest.getFirstName());
        patron.setLastName(patronRequest.getLastName());
        patron.setEmail(patronRequest.getEmail());
        patron.setPhoneNumber(patronRequest.getPhoneNumber());

        Patron updatedPatron = patronRepository.save(patron);
        return mapToDTO(updatedPatron);
    }

    public void deletePatron(Integer patronId) {
        patronRepository.deleteById(patronId);
    }

    private PatronResponseDTO mapToDTO(Patron patron) {
        return new PatronResponseDTO(patron.getPatronID(), patron.getFirstName(), patron.getLastName(), patron.getEmail(), patron.getPhoneNumber());
    }
}