package fr.coffee_machine.data;

import fr.coffee_machine.logic.DrinkType;
import java.util.EnumMap;

public class History {

    private static final EnumMap<DrinkType, Integer> orders = new EnumMap<>(DrinkType.class);

    private History() {
    }

    public static void incrementNumberOf(DrinkType drinkType) {
        orders.merge(drinkType, 1, Integer::sum);
    }

    public static int getNumberOf(DrinkType drinkType) {
        return orders.get(drinkType);
    }

    public static Double getTotalAmount() {
        return orders.entrySet().stream().mapToDouble(x -> (x.getKey().price * x.getValue())).sum();
    }

    public static void clear() {
        orders.clear();
    }
}
