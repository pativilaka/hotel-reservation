package model.entities;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private Integer roomNumber;
    private LocalDate checkin;
    private LocalDate checkout;

    public Reservation(){}

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) {
        this.roomNumber = roomNumber;
        this.checkin = checkin;
        this.checkout = checkout;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public LocalDate getCheckin() {
        return checkin;
    }

    public LocalDate getCheckout() {
        return checkout;
    }

     public long duration(){
        // long duration = ChronoUnit.DAYS.between(checkout, checkin);
        // long duration = ChronoUnit.DAYS.between(checkin, checkout);
        long duration = getCheckin().until(getCheckout(), ChronoUnit.DAYS);
        return duration;
    }

    public String updateDates(LocalDate checkin, LocalDate checkout){
        LocalDate now = LocalDate.now();
        if (checkin.isBefore(now) || checkout.isBefore(now)){
            return "Error in reservation: Reservation dates for update must be future dates";
        }
        if (!checkout.isAfter(checkin)){
            return "Error in reservation: Check-out date must be after check-in date!";
        }
        this.checkin = checkin;
        this.checkout = checkout;
        return null;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Reservation: Room " + getRoomNumber());
        sb.append(", check-in: " + getCheckin().format(fmt));
        sb.append(", check-out: " + getCheckout().format(fmt));
        sb.append(", " + duration());
        sb.append(" nights.");
        return sb.toString();
    }

}
