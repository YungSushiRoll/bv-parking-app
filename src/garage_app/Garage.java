package garage_app;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Garage {

    private List<Ticket> tickets = new ArrayList<>();
    private int id = 1;
    private Random rand = new Random();
    private double totalCollected = 0.00;
    private double idCollected = 0.00;
    private double lostCollected = 0.00;
    private int checkedIn = 0;
    private int totalCheckedIn = 0;
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

    public void exitMsg() throws InterruptedException {
        System.out.print("\nExiting System");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
        Thread.sleep(1000);
        System.out.print(".");
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
        totalCheckedIn++;
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
                            System.out.println("found it");
                            foundTix = true;
                            timeDiff = (leaveTime - ticket.getEnterTime());
                            // charge 5 + $1 for each extra hour
                            // add to idCollected
                            // add to totalCollected
                            System.out.println(ticket.getTicketId() + "Entered " + ticket.getEnterTime() + "\nLeft"
                                   + leaveTime + "\ndiff" + timeDiff +"\nIndex"+ tickets.indexOf(ticket));
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
                    tickets.remove(rand.nextInt(tickets.size()));
                    // charge $25.00
                    // add to lostCollected
                    // add to totalCollected
                    checkedIn--;
                    checkedOutWithLost++;
                    break;
            }
        } while ((!checkoutResp.equals("1") && !checkoutResp.equals("2")) || !foundTix);
        for (int i = 0; i <= tickets.size() - 1; i++)
        {
            System.out.println(tickets.get(i) +"\n"+ tickets.get(i).getTicketId());
        }
    }

    public void closingTime() throws InterruptedException {
        System.out.println("*Speakers start to blast \"Closing Time\" by Semisonic*");
        Thread.sleep(1500);
        System.out.println(""checkedOutWithId);
        System.out.println(checkedOutWithLost);
        System.out.println("Total Money: " + totalCollected + "collected from " + totalCheckedIn);
    }
}
