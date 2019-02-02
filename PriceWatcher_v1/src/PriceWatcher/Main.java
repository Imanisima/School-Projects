package PriceWatcher;

/*
* TO DO:
*   Input error catch for when no options are choise
*   Open url in terminal when chosen [view page]
*   Change price and percentage change
*
* */

public class Main {

    private void run() {
        Item item = new Item();
        ConsoleUI ui = new ConsoleUI(item);

        ui.showWelcome();
        ui.showItem();

        int request;

        do {

            request = ui.promptUser();

            switch (request){
                case ConsoleUI.CHECKPRICE:
                    System.out.println("Updating Price");
                    ui.showUpdatedPrice();
                    break;
                case ConsoleUI.VIEWPAGE:
                    System.out.println("Showing Page");
                    Item.showPage();
                    break;
            }

            System.out.println("-------------------------------------");

        } while(request != ConsoleUI.QUITAPP);

        System.out.println("Terminating Program.");
    }


    public static void main(String[] args) {
        new Main().run();
    }
}
