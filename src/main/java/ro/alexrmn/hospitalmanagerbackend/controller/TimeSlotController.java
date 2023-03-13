package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.alexrmn.hospitalmanagerbackend.model.TimeSlot;
import ro.alexrmn.hospitalmanagerbackend.service.TimeSlotService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timeslots")
@CrossOrigin
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @GetMapping
    public ResponseEntity<List<TimeSlot>> getTimeSlots() {
        List<TimeSlot> timeSlots = timeSlotService.getTimeSlots();
        return ResponseEntity.ok().body(timeSlots);
    }
}
