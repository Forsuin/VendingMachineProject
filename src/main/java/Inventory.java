import java.util.HashMap;
import java.util.Iterator;

public class Inventory implements Iterable<ItemInfo> {
    private HashMap<Integer, ItemInfo> items;


    Inventory() {
        items = new HashMap<>();
    }

    public void stockItem(String itemID) {
        getInfo(itemID).addItem();
    }

    public void removeItem(String itemID) {
        getInfo(itemID).removeItem();
    }

    public int getQuantity(String itemID) {
        return getInfo(itemID).getQuantity();
    }

    public double getPrice(String itemID) {
        return getInfo(itemID).getPrice();
    }

    public void setPrice(String itemID, double price) {
        getInfo(itemID).setPrice(price);
    }

    public int getNumItems() {
        return items.size();
    }

    public String getName(String itemID) {
        return getInfo(itemID).getItem().name();
    }

    //So for-each loops can work
    @Override
    public Iterator<ItemInfo> iterator() {
        return items.values().iterator();
    }

    private ItemInfo getInfo(String itemID) {
        return items.get(Integer.parseInt(itemID));
    }
}
