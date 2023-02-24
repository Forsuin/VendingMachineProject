import java.util.HashMap;
import java.util.Iterator;

public class Inventory implements Iterable<ItemInfo> {
    private final HashMap<Integer, ItemInfo> items;


    Inventory() {
        items = new HashMap<>();
    }

    public void addItem(ItemInfo item, Integer ID) {
        items.put(ID, item);
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

    public Integer getID(String name) {
        for (var entry : items.entrySet()) {
            if (entry.getValue().getItem().name().equals(name)) {
                return entry.getKey();
            }
        }
        return null;
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
