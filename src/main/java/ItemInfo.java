public class ItemInfo {
    private Item item;
    private double price;
    private int quantity;


    ItemInfo(Item item, double price, int quantity){
        this.item = item;
        this.price = price;
        this.quantity = quantity;
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

        if(quantity > 10){
            quantity = 10;
        }
    }

    public void removeItem() {
        quantity--;

        if(quantity < 0){
            quantity = 0;
        }
    }
}
