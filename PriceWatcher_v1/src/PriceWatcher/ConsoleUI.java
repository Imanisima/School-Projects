package PriceWatcher;
import java.util.Scanner;

class ConsoleUI {
    private Item item;
    Scanner userChoice = new Scanner(System.in);

    static final int CHECKPRICE = 1;

    static final int VIEWPAGE = 2;

    static final int QUITAPP = -1;

    public ConsoleUI(Item item) {
        this.item = item;
    }

    public void showWelcome() {
        System.out.println("WELCOME TO Price Watcher!");
    }

    /* Used for testing purposes! Need to avoid magic numbers */
    public void showItem() {
        System.out.println();

        Item.getName("Google - Home Hub");
        Item.getUrl("https://www.bestbuy.com/site/google-home-hub-with-google-assistant-chalk/6290313.p?skuId=6290313");
        Item.getPrice(99.99);
        Item.getPriceChange(0.00); // (a - b) / a * 100
        Item.getDateAdded("01/30/2019  ($99.99)");
    }


    public int promptUser() {
        System.out.println();

        System.out.printf("Please choose an option. Enter: \n %d : Check Price\n %d : View Page\n%d : Quit", CHECKPRICE, VIEWPAGE, QUITAPP);

        System.out.println();

        return userChoice.nextInt();
    }
}