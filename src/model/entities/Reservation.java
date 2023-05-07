package model.entities;

import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reservation {
    private Integer roomNumber;
    private LocalDate checkin;
    private LocalDate checkout;

    public Reservation(){}

    private static DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Reservation(Integer roomNumber, LocalDate checkin, LocalDate checkout) throws DomainException {
        validation(checkin, checkout);
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

    public void updateDates(LocalDate checkin, LocalDate checkout) throws DomainException{
        validation(checkin, checkout);
        this.checkin = checkin;
        this.checkout = checkout;

    }

    public void validation(LocalDate checkin, LocalDate checkout) throws DomainException{
        LocalDate now = LocalDate.now();
        if (checkin.isBefore(now) || checkout.isBefore(now)){
            throw new DomainException(
                    "Error in reservation: Reservation dates for update must be future dates");
        }
        if (!checkout.isAfter(checkin)){
            throw new DomainException(
                    "Error in reservation: Check-out date must be after check-in date!");
        }
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
