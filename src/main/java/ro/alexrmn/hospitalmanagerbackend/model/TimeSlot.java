package ro.alexrmn.hospitalmanagerbackend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;



@RequiredArgsConstructor
@Getter
public enum TimeSlot {
    TIME_SLOT_1("9:00 AM - 10:00 AM"),
    TIME_SLOT_2("10:00 AM - 11:00 AM"),
    TIME_SLOT_3("11:00 AM - 12:00 PM"),
    TIME_SLOT_4("12:00 PM - 1:00 PM"),
    TIME_SLOT_5("2:00 PM - 3:00 PM"),
    TIME_SLOT_6("3:00 PM - 4:00 PM");

    private final String timeSlot;


}