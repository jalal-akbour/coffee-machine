package fr.coffee_machine.logic;

import fr.coffee_machine.maker.DrinkMaker;
import fr.coffee_machine.services.BeverageQuantityChecker;
import fr.coffee_machine.services.EmailNotifier;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CoffeeMachineShortageTest {

    @Mock
    private DrinkMaker drinkMaker;
    @InjectMocks
    private Order order = new Order();

    @Mock
    private BeverageQuantityChecker beverageQuantityChecker;
    @Mock
    private EmailNotifier emailNotifier;

    @Test
    public void should_sendNotification_when_shortageQuantityIsEmpty() {
        //Given
        order.setDrinkType(DrinkType.COFFEE);
        order.setNumSugar(1);

        Mockito.when(beverageQuantityChecker.isEmpty(Mockito.anyString())).thenReturn(true);
        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(emailNotifier).notifyMissingDrink(DrinkType.COFFEE.toString());
        Mockito.verify(drinkMaker).make("M:There is a shortage, a notification has been sent to the company");
    }

    @Test
    public void should_makeCoffee_when_shortageQuantityIsNotEmpty() {
        //Given
        order.setDrinkType(DrinkType.COFFEE);
        order.setNumSugar(1);
        order.setGivingMoney(1);

        Mockito.when(beverageQuantityChecker.isEmpty(Mockito.anyString())).thenReturn(false);
        //When
        order.sendOrderToMaker();

        //Then
        Mockito.verify(drinkMaker).make("C:1:0");
    }
}
