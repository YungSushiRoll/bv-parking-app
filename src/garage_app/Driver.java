package garage_app;

import java.util.Scanner;

public class Driver {

    public static void main(String[] args) throws InterruptedException {

        Scanner keyboard = new Scanner(System.in);
        Garage garage = new Garage();

        String choice;
        do {
            garage.printHeader();
            choice = keyboard.nextLine();
            switch (choice){
                case "1" :
                    garage.checkin();
                    // have a do check in thingy
                break;
                case "2" :
                    garage.checkout();
                    // do check out thingy
                break;
                case "3" :
                    garage.closingTime();
                    // do close garage thingy
                break;
                default:
                    if (choice.equals("4"))
                    {
                        garage.exitMsg();
                        break;
                    }
                    garage.invalidHeader();
                    Thread.sleep(2000);
            }
        } while (!choice.equals("4"));

    }
}
