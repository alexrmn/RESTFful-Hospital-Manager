package ro.alexrmn.hospitalmanagerbackend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ETimeSlot {
    SLOT_9_10("9:00 AM - 10:00 AM"),
    SLOT_10_11("10:00 AM - 11:00 AM"),
    SLOT_11_12("11:00 AM - 12:00 PM"),
    SLOT_12_13("12:00 PM - 1:00 PM"),
    SLOT_13_14("1:00 PM - 2:00 PM"),
    SLOT_14_15("2:00 PM - 3:00 PM"),
    SLOT_15_16("3:00 PM - 4:00 PM"),
    SLOT_16_17("4:00 PM - 5:00 PM");

    private final String value;

}
