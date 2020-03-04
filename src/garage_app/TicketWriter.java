package garage_app;

import java.io.*;
import java.util.List;

public class TicketWriter {

    public void writeTicketFile(String fileName, List<Ticket> ticketList) throws IOException {

        FileOutputStream fos = new FileOutputStream(fileName);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        for (Ticket aTicket : ticketList) {
            oos.writeObject(aTicket);
        }

        oos.close();
    }
}
