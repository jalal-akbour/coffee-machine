package fr.coffee_machine.logic;

import fr.coffee_machine.data.History;
import fr.coffee_machine.maker.DrinkMaker;

public class Order {

    private static final String SEPARATOR = ":";
    private DrinkType drinkType;
    private int nbSugar;
    private String orderMsg;
    private double givingMoney;
    private boolean isHotDrink;


    private DrinkMaker drinkMaker;

    public void setDrinkType(DrinkType drinkType) {
        this.drinkType = drinkType;
    }

    public void setNumSugar(int nbSugar) {
        this.nbSugar = nbSugar;
    }

    public void setGivingMoney(double givingMoney) {
        this.givingMoney = givingMoney;
    }

    public void setHotDrink(boolean hotDrink) {
        isHotDrink = hotDrink;
    }

    private String translateOrder() {
        StringBuilder translatedOrder = new StringBuilder();
        StringBuilder msgBuilder = new StringBuilder();

        translatedOrder.append(drinkType.value);
        if (isHotDrink) {
            translatedOrder.append("h");
        }
        translatedOrder.append(SEPARATOR);

        msgBuilder.append("Preparation of ").append(drinkType);

        if (nbSugar > 0) {
            translatedOrder.append(nbSugar);
            translatedOrder.append(SEPARATOR);
            translatedOrder.append("0");

            msgBuilder.append(" with ").append(nbSugar).append(" sugars and stick");
        } else {
            translatedOrder.append(SEPARATOR);

            msgBuilder.append(" without sugar and without stick");
        }
        orderMsg = msgBuilder.toString();
        return translatedOrder.toString();
    }

    public void sendOrderToMaker() {
        double missingAmount = calculateMissingAmount();

        if (missingAmount > 0) {
            orderMsg = "You must add " + missingAmount + " euro";
        } else {
            drinkMaker.make(translateOrder());
            History.incrementNumberOf(drinkType);
        }
        sendMessageToMaker();
    }

    private void sendMessageToMaker() {
        drinkMaker.make("M:" + orderMsg);
    }

    private double calculateMissingAmount() {
        return (drinkType.price * 100 - givingMoney * 100) / 100;
    }
}
