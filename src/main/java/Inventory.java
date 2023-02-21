import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Inventory {
    Map<Item, Integer> items = new HashMap<>(Map.of(
            //Item(Name, price), quantity
            new Item("Mountain Dew", 1.75), 10,
            new Item("Monster", 3.00), 10,
            new Item("Water", 2.00), 10,
            new Item("Coke", 1.75), 10,
            new Item("Bang", 3.00), 10,
            new Item("Dr. Pepper", 1.75), 10
    ));


    public int getNumItems(Item item){
        Integer amount = items.get(item);

        if(amount == null){
            throw new NoSuchElementException(String.format("The key [%s] does not exist in inventory", item.toString()));
        }

        return amount;
    }

    public void addItems(Item item, int amount){
        if(!items.containsKey(item)){
            throw new NoSuchElementException(String.format("The key [%s] does not exist in inventory", item.toString()));
        }

        int newAmount = items.get(item) + amount;

        items.put(item, Math.min(newAmount, 10));
    }

    public void removeItems(Item item, int amount){
        if(!items.containsKey(item)){
            throw new NoSuchElementException(String.format("The key [%s] does not exist in inventory", item.toString()));
        }

        int newAMount = items.get(item) + amount;

        items.put(item, Math.max(newAMount, 0));
    }

    public void setItemPrice(Item item, double price){

        items.put(new Item(item.getName(), price), items.remove(item));
    }
}
