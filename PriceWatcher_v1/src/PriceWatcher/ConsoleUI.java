package PriceWatcher;
import java.util.Scanner;

class ConsoleUI {
    private Item item;
    private Scanner userChoice = new Scanner(System.in);

    static final int CHECKPRICE = 1;
    static final int VIEWPAGE = 2;
    static final int QUITAPP = -1;

    ConsoleUI(Item item) {
        this.item = item;
    }

    void showWelcome() {
        System.out.println("WELCOME TO Price Watcher!");
    }

    void showItem() {
        System.out.println();

        Item.getName();
        Item.getUrl();
        Item.getPrice();
        Item.getPriceChange();
        Item.getDateAdded();
    }

    void showUpdatedPrice(){
        System.out.println();

        Item.getName();
        Item.getUrl();
        Item.getUpdatedPrice();
        Item.getPriceChange();
        Item.getDateAdded();
    }


    int promptUser() {
        System.out.println();

        System.out.printf("Please choose an option. Enter: \n %d : Check Price\n %d : View Page\n%d : Quit", CHECKPRICE, VIEWPAGE, QUITAPP);

        System.out.println();

        return userChoice.nextInt();
    }
}