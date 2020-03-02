package garage_app;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Garage {

    private List<Ticket> tickets = new ArrayList<>();
    private int id = 1;
    private Random rand = new Random();
    private int totalCollected = 0;
    private int idCollected = 0;
    private int lostCollected = 0;
    private int checkedIn = 0;
    private int checkedOutWithId = 0;
    private int checkedOutWithLost = 0;

//    public Garage(List<Vehicle> vehicles, List<Ticket> tickets){
//        this.vehicles = vehicles;
//        this.tickets = tickets;
//    }

    public void basicHeader() {
        System.out.println(
                "\n*************************\n" +
                "Best Value Parking Garage\n" +
                "*************************\n");
    }

    public void printHeader() {
        if (checkedIn > 0) {
            basicHeader();
            System.out.println(
                    "1 - Check-in\n" +
                    "2 - Check-out\n" +
                    "3 - Close Garage\n" +
                    "4 - Exit");
        } else {
            basicHeader();
            System.out.println(
                    "1 - Check-in\n" +
                    "3 - Close Garage\n" +
                    "4 - Exit");
        }
    }

    public void invalidHeader()
    {
        System.out.println(
                "\n*************************\n" +
                "Not a valid option choose again\n" +
                "*************************\n");
    }

    public void pause() throws InterruptedException{
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
    }

    public void receipt(Ticket ticket, String enteredTime, String timeLeft){
        System.out.println("Receipt for vehicle id " + ticket.getTicketId());
        System.out.println("Hours Parked: " + enteredTime + " - " + timeLeft);
    }

    public void exitMsg() throws InterruptedException {
        System.out.print("\nExiting System");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
    }

    public void checkin(){

        int enterTime = (rand.nextInt(6) + 7);
        System.out.println(enterTime);
        Ticket newTicket = new Ticket(id, enterTime);
        tickets.add(newTicket);
        for (Ticket ticket : tickets) {
            if (ticket.getTicketId() == id)
            {
                id++;
            }
            System.out.println(ticket.getTicketId() + " " + ticket.getEnterTime());
        }
        checkedIn++;
    }

    public void checkout() throws InterruptedException {

        int leaveTime = (rand.nextInt(12) + 12);
        int timeDiff;
        Scanner keyboard = new Scanner(System.in);
        String checkoutResp;
        String ticketResp;
        boolean foundTix;
        do {
            basicHeader();
            foundTix = true;
            System.out.println(
                    "1 - Check out with ticket ID\n" +
                    "2 - Lost ticket");
            checkoutResp = keyboard.nextLine();
            System.out.println(checkedIn);
            switch (checkoutResp)
            {
                case "1" :
                    System.out.println("Enter Ticket ID:");
                    ticketResp = keyboard.nextLine();
                    int newResp = Integer.parseInt(ticketResp);
                    for (Ticket ticket : tickets) {
                        if (newResp == ticket.getTicketId()) {
                            int stayCost = 5;
                            System.out.println("Found it!");
                            foundTix = true;
                            timeDiff = (leaveTime - ticket.getEnterTime());
                            // Time formatting for output
                            LocalTime leaveTimeFormatter = LocalTime.of(leaveTime, 0);
                            LocalTime enterTimeFormatter = LocalTime.of(ticket.getEnterTime(), 0);
                            // checking to see if time spent is greater than 3 hours
                            if (timeDiff > 3){
                                stayCost += (timeDiff - 3);
                            }
                            // total collected from people with ticket Id
                            idCollected += stayCost;
                            // totals money collected
                            totalCollected += idCollected;
                            System.out.println("Time to pay!");
                            System.out.println("You owe $"+ stayCost);
                            pause();
                            System.out.print("\nPaid!\n");
                            Thread.sleep(1000);
                            basicHeader();
                            receipt(
                                    ticket,
                                    enterTimeFormatter.format(DateTimeFormatter.ofPattern("hh:mm a")),
                                    leaveTimeFormatter.format(DateTimeFormatter.ofPattern("hh:mm a"))
                            );

                            tickets.remove(ticket);
                            checkedIn--;
                            checkedOutWithId++;
                            break;
                        } else {
                            foundTix = false;
                        }
                    }
                    if (!foundTix){
                        System.out.println(
                                "Hm, seems like we don't have a ticket with that ID.\n" +
                                "Check again"
                        );
                        Thread.sleep(1000);
                    }
                    // check ticket id with Ticket list
                    break;
                case "2" :
                    System.out.println("Lost your ticket huh? tsk tsk tsk");
                    int lostCost = 25;
                    System.out.println("Since you lost your ticket you owe $" + lostCost);
                    System.out.println("Time to pay!");
                    pause();
                    System.out.print("\nPaid!\n");
                    Thread.sleep(1000);
                    basicHeader();
                    System.out.println("Receipt for Lost Ticket\n$" + lostCost);
                    lostCollected += lostCost;
                    totalCollected += lostCollected;
                    tickets.remove(rand.nextInt(tickets.size()));
                    checkedIn--;
                    checkedOutWithLost++;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + checkoutResp);
            }
        } while ((!checkoutResp.equals("1") && !checkoutResp.equals("2")) || !foundTix);
        for (int i = 0; i <= tickets.size() - 1; i++)
        {
            System.out.println(tickets.get(i) +"\n"+ tickets.get(i).getTicketId());
        }
    }

    public void closingTime() throws InterruptedException {
        System.out.println("*Speakers start to blast \"Closing Time\" by Semisonic*\n");
        Thread.sleep(1500);
        basicHeader();
        System.out.println("Activity to Date\n");
        System.out.println("$" + idCollected + " was collected from " + checkedOutWithId + " Check-Ins");
        System.out.println("$" + lostCollected + " was collected from " + checkedOutWithLost + " Lost Tickets");
        System.out.println("$" + totalCollected + " was collected overall");
    }
}
