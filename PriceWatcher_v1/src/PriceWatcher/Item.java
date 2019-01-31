package PriceWatcher;

import java.awt.*;
import java.net.URI;

class Item {

    private static final String WEBURL = "https://www.bestbuy.com/site/google-home-hub-with-google-assistant-chalk/6290313.p?skuId=6290313";
    private static final String NAME = "Google - Home Hub";
    private static final String DATEADDED = "01/30/2019  ($99.99)";

    static void getName() {
        System.out.printf("Name:\t%s\n", NAME);
    }

    /* TO DO: open WEBURL through terminal */
    static void getUrl() {
        System.out.printf("URL:\t%s\n", WEBURL);
    }

    static void getPrice() {
        double PRICE = 99.99;
        System.out.printf("Price:\t$%s\n", PRICE);
    }

    /* Ensure that PRICE fluctuates each time for testing */
    static void getPriceChange() {
        double priceChange = 0.00;
        System.out.println("Change:\t" + priceChange + "%");
    }

    /* Stays the same --- Don't touch*/
    static void getDateAdded() {
        System.out.printf("Added:\t%s\n", DATEADDED);
    }

    static void showPage() {
        try {
            URI u = new URI(WEBURL);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(u);
        } catch (Exception e) {
            System.out.println("Cannot load site.");
        }
    }
}
