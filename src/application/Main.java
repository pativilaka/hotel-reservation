package application;

import model.entities.Reservation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Room number: ");
        int room = sc.nextInt();
        System.out.print("Check-in date (dd/MM/yyyy): ");
        LocalDate checkin = LocalDate.parse(sc.next(), fmt);
        System.out.print("Check-out date (dd/MM/yyyy): ");
        LocalDate checkout = LocalDate.parse(sc.next(), fmt);

        LocalDate now = LocalDate.now();
        if (checkin.isBefore(now) || checkout.isBefore(now)){
            System.out.println("Error in reservation: Reservation dates for update must be future dates");
        }
        else if (!checkout.isAfter(checkin)){
            System.out.println("Error in reservation: Check-out date must be after check-in date!");
        }
        else {
            Reservation reservation = new Reservation(room, checkin, checkout);
            System.out.println(reservation);
            System.out.println();

            System.out.println("Enter data to update the reservation:");
            System.out.print("Check-in date (dd/MM/yyyy): ");
            LocalDate checkinUp = LocalDate.parse(sc.next(), fmt);
            System.out.print("Check-out date (dd/MM/yyyy): ");
            LocalDate checkoutUp = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            if (checkinUp.isBefore(now) || checkoutUp.isBefore(now)){
                System.out.println("Error in reservation: Reservation dates for update must be future dates");
            }
            else if (!checkoutUp.isAfter(checkinUp)){
                System.out.println("Error in reservation: Check-out date must be after check-in date!");
            }
            else {
                reservation.updateDates(checkinUp, checkoutUp);
                System.out.println(reservation);
            }
        }


        sc.close();
    }
}