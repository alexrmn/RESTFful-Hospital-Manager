package ro.alexrmn.hospitalmanagerbackend.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Getter
public enum ETimeSlot {
    SLOT_9_10("9:00 - 10:00"),
    SLOT_10_11("10:00 - 11:00"),
    SLOT_11_12("11:00 - 12:00"),
    SLOT_12_13("12:00 - 13:00"),
    SLOT_13_14("13:00 - 14:00"),
    SLOT_14_15("14:00 - 15:00"),
    SLOT_15_16("15:00 - 16:00"),
    SLOT_16_17("16:00 - 17:00");

    private final String value;

    public LocalTime getTime() {
        String hour = value.split("-")[0].trim();
        System.out.println(hour);
        return LocalTime.parse(hour, DateTimeFormatter.ofPattern("H:mm"));
    }

}
