package PriceWatcher;

class Item {

    public static void getName(String name){
        System.out.println("Name:\t" + name);
    }

    /* TO DO: open url through terminal */
    public static void getUrl(String webUrl){
        System.out.println("URL:\t" + webUrl);
    }

    public static void getPrice(double price){
        System.out.println("Price:\t$" + price);
    }

    /* Ensure that price fluctuates each time for testing */
    public static void getPriceChange(double priceChange){
        System.out.println("Change:\t" + priceChange + "%");
    }

    /* Stays the same --- Don't touch*/
    public static void getDateAdded(String dateAdded){
        System.out.println("Added:\t" + dateAdded);
    }
}
