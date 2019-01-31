package PriceWatcher;
import java.util.Scanner;

public class ConsoleUI {
    private Item item;
    Scanner userChoice = new Scanner(System.in);

    public ConsoleUI(Item item) {
        this.item = item;
    }

    public void showWelcome() {
        System.out.println("Welcome to Price Watcher!");
    }

    public void showItem() {
        System.out.println("\nItem details...");
    }

    public static final int checkPrice = 1;

    public static final int viewPage = 2;

    public static final int quitApp = -1;

    public int promptUser() {

        System.out.printf("Please choose an option. Enter: \n %d : Check Price\n %d : View Page\n%d : Quit", checkPrice, viewPage, quitApp);

        System.out.println();

        return userChoice.nextInt();
    }
}
