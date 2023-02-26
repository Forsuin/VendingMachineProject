/**
 * Holds info abouts items for inventory
 */
public class ItemInfo {
    private final Item item;
    private double price;
    private int quantity;


    /**
     * @param name Name of item
     * @param price Price of item
     */
    ItemInfo(String name, double price){
        this.item = new Item(name);
        this.price = price;
        this.quantity = 10;
    }

    /**
     * @return double, price of item
     */
    public double getPrice() {
        return price;
    }

    /**
     * @param price Price to set item to
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * @return Reference to Item
     */
    public Item getItem() {
        return item;
    }

    /**
     * @return int, Number of this itme
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Increments quantity of item
     */
    public void addItem() {
        quantity++;

        if (quantity >= 10) {
            quantity = 10;
            System.out.println("The machine can't hold anymore of this item");
        }
    }

    /**
     * Decrements quantity of item
     */
    public void removeItem() {
        quantity--;

        if (quantity <= 0) {
            quantity = 0;
            System.out.println("The machine is out of this item");
        }
    }
}
