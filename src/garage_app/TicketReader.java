package garage_app;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class TicketReader {

    public List<Ticket> readTicketFile(String fileName) throws IOException {

        FileInputStream fis = new FileInputStream(fileName);
        ObjectInputStream ois = new ObjectInputStream(fis);

        List<Ticket> theTickets = new ArrayList<>();

        Object theTicket;
        try {
            while ((theTicket = ois.readObject()) != null) {
                Ticket aTicket = (Ticket) theTicket;
                theTickets.add(aTicket);
                System.out.println("here");
            }
        } catch (EOFException | ClassNotFoundException ignored) {
        }

        return theTickets;
    }
}
