package ro.alexrmn.hospitalmanagerbackend.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.alexrmn.hospitalmanagerbackend.model.TimeSlot;
import ro.alexrmn.hospitalmanagerbackend.service.TimeSlotService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/timeslots")
@CrossOrigin
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    @GetMapping("/{date}/{doctorId}")
    public ResponseEntity<List<TimeSlot>> getTimeSlots(@PathVariable LocalDate date, @PathVariable Long doctorId) {
        List<TimeSlot> timeSlots = timeSlotService.getFreeTimeSlotsByDateAndDoctor(date, doctorId);
        return ResponseEntity.ok().body(timeSlots);
    }
}
