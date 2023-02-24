public class ItemInfo {
    private final Item item;
    private double price;
    private int quantity;


    ItemInfo(String name, double price){
        this.item = new Item(name);
        this.price = price;
        this.quantity = 10;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void addItem() {
        quantity++;

        if (quantity > 10) {
            quantity = 10;
            System.out.println("The machine can't hold anymore of this item");
        }
    }

    public void removeItem() {
        quantity--;

        if (quantity < 0) {
            quantity = 0;
        }
    }
}
