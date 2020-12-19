package test;

import com.oms.bean.Order;
import com.oms.bean.OrderLine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author phong.cv173298
 * @created 19/12/2020 - 9:06 PM
 * @project oms
 */
@RunWith(value = Parameterized.class)
public class TotalCostWhiteBoxTest {
    private Order order;
    private float expected;

    public TotalCostWhiteBoxTest(Order order, float expected) {
        this.order = order;
        this.expected = expected;
    }

    @Parameterized.Parameters(name = "{index}: Order({0}) = totalCost{1}")
    public static Collection<Object[]> primeNumbers() {
        Order order1 = new Order();
        order1.setCustomerAddress("hn");
        List<OrderLine> orderLines1 = new ArrayList<>();
        OrderLine orderLine1 = new OrderLine();
        orderLine1.setProductCost(150000.0f);
        orderLine1.setProductQuantity(1);
        orderLine1.setWeight(1.2f);
        orderLines1.add(orderLine1);
        order1.setOrderLines((ArrayList<OrderLine>) orderLines1);

        Order order2 = new Order();
        order2.setCustomerAddress("hn");
        order2.setOrderLines(null);

        Order order3 = new Order();
        order3.setCustomerAddress("hn");
        List<OrderLine> orderLines3 = new ArrayList<>();
        OrderLine orderLine3 = new OrderLine();
        orderLine3.setProductCost(250000.0f);
        orderLine3.setProductQuantity(2);
        orderLine3.setWeight(-1.2f);
        orderLines3.add(orderLine3);
        order3.setOrderLines((ArrayList<OrderLine>) orderLines3);

        Order order4 = new Order();
        order4.setCustomerAddress("hn");
        List<OrderLine> orderLines4 = new ArrayList<>();
        OrderLine orderLine4 = new OrderLine();
        orderLine4.setProductCost(-150000.0f);
        orderLine4.setProductQuantity(1);
        orderLine4.setWeight(1.2f);
        orderLines4.add(orderLine4);
        order4.setOrderLines((ArrayList<OrderLine>) orderLines4);

        Order order5 = new Order();
        order5.setCustomerAddress("hn");
        List<OrderLine> orderLines5 = new ArrayList<>();
        OrderLine orderLine5 = new OrderLine();
        orderLine5.setProductCost(150000.0f);
        orderLine5.setProductQuantity(5);
        orderLine5.setWeight(3.4f);
        orderLines5.add(orderLine5);
        order5.setOrderLines((ArrayList<OrderLine>) orderLines5);

        Order order6 = new Order();
        order6.setCustomerAddress("no hn");
        List<OrderLine> orderLines6 = new ArrayList<>();
        OrderLine orderLine6 = new OrderLine();
        orderLine6.setProductCost(150000.0f);
        orderLine6.setProductQuantity(1);
        orderLine6.setWeight(0.4f);
        orderLines6.add(orderLine6);
        order6.setOrderLines((ArrayList<OrderLine>) orderLines6);

        return Arrays.asList(new Object[][]{
                {order1, 172000.0f},//C5
                {order2, 0},
                {order3, 0},
                {order4, 0},
                {order5, 750000.0f},
                {order6, 180000.0f}
        });
    }

    @Test
    public void testGetTotalCost() {
        assertTrue("fail: "+order.getTotalCost()+":"+expected, order.getTotalCost() == expected);
    }
}
