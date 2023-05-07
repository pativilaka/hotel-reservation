package application;

import model.entities.Reservation;
import model.exceptions.DomainException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            try {
                System.out.print("Room number: ");
                int room = sc.nextInt();
                System.out.print("Check-in date (dd/MM/yyyy): ");
                LocalDate checkin = LocalDate.parse(sc.next(), fmt);
                System.out.print("Check-out date (dd/MM/yyyy): ");
                LocalDate checkout = LocalDate.parse(sc.next(), fmt);


                Reservation reservation = new Reservation(room, checkin, checkout);
                System.out.println(reservation);
                System.out.println();

                System.out.println("Enter data to update the reservation:");
                System.out.print("Check-in date (dd/MM/yyyy): ");
                LocalDate checkinUp = LocalDate.parse(sc.next(), fmt);
                System.out.print("Check-out date (dd/MM/yyyy): ");
                LocalDate checkoutUp = LocalDate.parse(sc.next(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                reservation.updateDates(checkinUp, checkoutUp);
                System.out.println(reservation);

            }
            catch (DomainException e) {
                System.out.println(e.getMessage());
            }
            catch (RuntimeException e) {
                System.out.println("Unexpected error!");
            }

        sc.close();
    }
}