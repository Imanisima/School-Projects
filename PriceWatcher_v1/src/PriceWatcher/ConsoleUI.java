package PriceWatcher;

public class ConsoleUI {
    private Item item;

    public ConsoleUI(Item item) {
        this.item = item;
    }

    public void showWelcome() {
        System.out.println("Welcome to Price Watcher!");
    }

    public void showItem() {
        System.out.println("Item details...");
    }

    public int promptUser() {
        return -1;
    }
}
