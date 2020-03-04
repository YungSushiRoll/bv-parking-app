package garage_app;

import java.io.IOException;
import java.io.Serializable;
import java.util.Scanner;

public class Driver implements Serializable {

    public static void main(String[] args) throws InterruptedException, IOException {

        Scanner keyboard = new Scanner(System.in);
        Garage garage = new Garage();
        try {
            garage.openGarage();
            System.out.println("File Read");
        } catch (IOException e){
            garage.basicHeader();
            System.out.println("No Files");
        }

        String choice;
        do {
            garage.printHeader();
            choice = keyboard.nextLine();
            switch (choice) {
                case "1":
                garage.checkin();
                // have a do check in thingy
                break;
                case "2":
                garage.checkout();
                // do check out thingy
                break;
                case "3":
                garage.closingTime();
                // do close garage thingy
                break;
                default:
                if (choice.equals("4")) {
                    garage.exitMsg();
                    break;
                }
                garage.invalidHeader();
            }
        } while (!choice.equals("4"));

    }
}
