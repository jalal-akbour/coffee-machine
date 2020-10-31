package fr.coffee_machine.logic;

import fr.coffee_machine.data.History;
import fr.coffee_machine.maker.DrinkMaker;
import fr.coffee_machine.services.BeverageQuantityChecker;
import fr.coffee_machine.services.EmailNotifier;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineHistoryTest {

    @Mock
    private DrinkMaker drinkMaker;
    @Mock
    private BeverageQuantityChecker beverageQuantityChecker;
    @Mock
    private EmailNotifier emailNotifier;

    @InjectMocks
    private Order coffeeOrder = new Order();
    @InjectMocks
    private Order teaOrder = new Order();
    @InjectMocks
    private Order chocolateOrder = new Order();
    @InjectMocks
    private Order orangeOrder = new Order();

    @Before
    public void setDefaultValues() {
        History.clear();

        coffeeOrder.setDrinkType(DrinkType.COFFEE);
        teaOrder.setDrinkType(DrinkType.TEA);
        chocolateOrder.setDrinkType(DrinkType.CHOCOLATE);
        orangeOrder.setDrinkType(DrinkType.ORANGE);

        coffeeOrder.setGivingMoney(1);
        teaOrder.setGivingMoney(1);
        chocolateOrder.setGivingMoney(1);
        orangeOrder.setGivingMoney(1);
    }

    @Test
    public void should_returnHistoryNbrOfCoffeeEqualToOne_when_firstCoffeeOrder() {
        //When
        coffeeOrder.sendOrderToMaker();

        //Then
        Assert.assertEquals(1, History.getNumberOf(DrinkType.COFFEE));
    }

    @Test
    public void should_returnHistoryNbrOfCoffeeEqualToTwo_when_secondCoffeeOrder() {
        //When
        coffeeOrder.sendOrderToMaker();
        coffeeOrder.sendOrderToMaker();
        //Then
        Assert.assertEquals(2, History.getNumberOf(DrinkType.COFFEE));
    }

    @Test
    public void should_returnHistoryTotalAmount_when_secondCoffeeOrder() {
        //When
        coffeeOrder.sendOrderToMaker();
        coffeeOrder.sendOrderToMaker();

        //Then
        Assert.assertEquals(Double.valueOf(1.2), History.getTotalAmount());
    }

    @Test
    public void should_returnHistoryOfEachDrink_when_multipleOrders() {
        //When
        teaOrder.sendOrderToMaker();

        coffeeOrder.sendOrderToMaker();
        coffeeOrder.sendOrderToMaker();

        chocolateOrder.sendOrderToMaker();
        chocolateOrder.sendOrderToMaker();
        chocolateOrder.sendOrderToMaker();

        orangeOrder.sendOrderToMaker();
        orangeOrder.sendOrderToMaker();

        //Then
        Assert.assertEquals(1, History.getNumberOf(DrinkType.TEA));
        Assert.assertEquals(2, History.getNumberOf(DrinkType.COFFEE));
        Assert.assertEquals(3, History.getNumberOf(DrinkType.CHOCOLATE));
        Assert.assertEquals(2, History.getNumberOf(DrinkType.ORANGE));

        Assert.assertEquals(Double.valueOf(4.3), History.getTotalAmount());
    }

}
