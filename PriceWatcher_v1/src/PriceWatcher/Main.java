package PriceWatcher;

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

        int request = -1;
        do {
            ui.showItem();
            request = ui.promptUser();

            switch (request){
                case 1: // Update Price
                    break;
                case 2: // ....
                    break;
            }


        } while(request != -1);
    }


    public static void main(String[] args) {
        new Main().run();
    }
}
