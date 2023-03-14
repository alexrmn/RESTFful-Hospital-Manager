package ro.alexrmn.hospitalmanagerbackend.service;

import ro.alexrmn.hospitalmanagerbackend.model.TimeSlot;

import java.time.LocalDate;
import java.util.List;

public interface TimeSlotService {

    List<TimeSlot> getTimeSlots();

    List<TimeSlot> getFreeTimeSlotsByDateAndDoctor(LocalDate date, Long doctorId);
}
