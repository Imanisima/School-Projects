package PriceWatcher;

/*
* Name:
* Date:
*
* Version: 1
*
* */

public class Main {

    public void run(){
        Item item = new Item();

        ConsoleUI ui = new ConsoleUI(item);
        ui.showWelcome();

        /*
        *  Repeat until user is asked to quit
        *  print the item
        *  prompt the user
        *  process
        *
        * */

        int request;
        do {

            ui.showItem();
            request = ui.promptUser();

            switch (request){
                case ConsoleUI.checkPrice: // Check Price
                    break;
                case ConsoleUI.viewPage: // Send user to original web page
                    break;
            }


        } while(request != ConsoleUI.quitApp);
    }


    public static void main(String[] args) {
        new Main().run();
    }
}
