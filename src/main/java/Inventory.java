import java.util.HashMap;
import java.util.Iterator;


/**
 * Represents the inventory of the vending machine
 */
public class Inventory implements Iterable<ItemInfo> {
    private final HashMap<Integer, ItemInfo> items;


    /**
     * Constructor, initializes inventory
     */
    Inventory() {
        items = new HashMap<>();
    }

    /**
     * Adds a new itemInfo to inventory, only used during startup
     * @param itemInfo Info about item to add to inventory
     * @param ID ID of itemInfo
     */
    public void addItem(ItemInfo itemInfo, Integer ID) {
        items.put(ID, itemInfo);
    }

    /**
     * Increments quantity of item by 1
     * @param itemID Item to increment
     */
    public void stockItem(String itemID) {
        getInfo(itemID).addItem();
    }

    /**
     * Decrements quantity of item by 1
     * @param itemID Item to decrement
     */
    public void removeItem(String itemID) {
        getInfo(itemID).removeItem();
    }

    /**
     * Returns ID of item given name
     * @param name Name of item to find ID for
     * @return Integer ID of item
     */
    public Integer getID(String name) {
        for (var entry : items.entrySet()) {
            if (entry.getValue().getItem().name().equals(name)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Returns price of item
     * @param itemID Item to find price for
     * @return Price as double
     */
    public double getPrice(String itemID) {
        return getInfo(itemID).getPrice();
    }

    /**
     * Sets price of item to inputted amount
     * @param itemID Item to change price of
     * @param price New price to change to
     */
    public void setPrice(String itemID, double price) {
        getInfo(itemID).setPrice(price);
    }

    /**
     * Gets the number of items in inventory
     * @return int
     */
    public int getNumItems() {
        return items.size();
    }

    /**
     * Gets the name of item given ID
     * @param itemID ID to find name for
     * @return String, name of item
     */
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
