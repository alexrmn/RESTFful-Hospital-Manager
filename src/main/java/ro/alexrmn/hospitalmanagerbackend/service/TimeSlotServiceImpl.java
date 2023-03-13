package ro.alexrmn.hospitalmanagerbackend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.alexrmn.hospitalmanagerbackend.model.TimeSlot;
import ro.alexrmn.hospitalmanagerbackend.repository.TimeSlotRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;

    @Override
    public List<TimeSlot> getTimeSlots() {
        return timeSlotRepository.findAll();
    }
}
