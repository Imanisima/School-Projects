package PriceWatcher;

/*
* TO DO:
*   Input error catch for when no options are choise
*   Open url in terminal when chosen [view page]
*   Change price and percentage change
*
* */

public class Main {

    private void run(){
        Item item = new Item();

        ConsoleUI ui = new ConsoleUI(item);
        ui.showWelcome();

        int request;
        do {
            ui.showItem();

            request = ui.promptUser();

            switch (request){
                case ConsoleUI.CHECKPRICE:
                    // include updated price
                    break;
                case ConsoleUI.VIEWPAGE:
                    // send user to original webpage
                    break;
            }

            System.out.println("-------------------------------------");

        } while(request != ConsoleUI.QUITAPP);

        System.out.println("Good-bye!");
    }


    public static void main(String[] args) {
        new Main().run();
    }
}
