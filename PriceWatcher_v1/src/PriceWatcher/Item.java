package PriceWatcher;

import java.awt.*;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

class Item {

    private static final String WEBURL = "https://www.bestbuy.com/site/google-home-hub-with-google-assistant-chalk/6290313.p?skuId=6290313";
    private static final String NAME = "Google - Home Hub";
    private static final double PRICE = 99.99;
    private static double NEWPRICE = 12;

    static void getName() {
        System.out.printf("Name:\t%s\n", NAME);
    }

    /* TO DO: open WEBURL through terminal */
    static void getUrl() {
        System.out.printf("URL:\t%s\n", WEBURL);
    }

    static void getPrice() {
        System.out.printf("Price:\t$%s\n", PRICE);
    }

    static void getUpdatedPrice(){
        System.out.printf("Updated Price:\t%s\n", NEWPRICE);
    }

    /* Ensure that PRICE fluctuates each time for testing */
    static void getPriceChange() {
        double priceChange = 0.00;
        System.out.println("Change: " + priceChange + "%");
    }

    static void getDateAdded() {
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("MM.dd.yyyy");
        System.out.printf("Date Added:\t%s\t(Initial Price: " + PRICE + ")\n", f.format(date));
//        System.out.printf(); // (a - b) / a * 100
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
