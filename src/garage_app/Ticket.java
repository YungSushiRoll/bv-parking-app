package garage_app;

public class Ticket {

    private int ticketId;
    private int enterTime;

    public Ticket(int ticketId, int enterTime){
        this.ticketId = ticketId;
        this.enterTime = enterTime;
    }

    public int getTicketId(){
        return ticketId;
    }

    public int getEnterTime(){
        return enterTime;
    }
}
