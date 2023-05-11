package venox.bot.models.user;

import java.util.HashMap;

public class Inventory {
    public int userId;
    public HashMap<String, Item> items = new HashMap<String, Item>();

    public Inventory(int userId) {
        this.userId = userId;
    }

    public void addItem(String itemName, int amount) {
        if (items.containsKey(itemName)) {
            Item item = items.get(itemName);
            item.value += amount;
            //updateDatabaseItemAmount(item);
        } else {
            //VenoxLog(items.get(itemName));
            Item newItem = new Item();
            newItem.id = -1;
            newItem.name = itemName;
            newItem.ownerId = this.userId;
            newItem.value = amount;
            //newItem.id = insertDatabaseItem(newItem);
            items.put(itemName, newItem);
        }
    }

    public void subItem(String itemName, int amount) {
        if (!items.containsKey(itemName)) {
            return;
        }
        Item item = items.get(itemName);
        if (item.value - amount <= 0) {
            //deleteDatabaseItem(item);
            items.remove(itemName);
            return;
        }
        item.value -= amount;
       //updateDatabaseItemAmount(item);
    }
}
