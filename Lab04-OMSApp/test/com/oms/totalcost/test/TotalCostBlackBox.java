import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.oms.bean.Book;
import com.oms.bean.Order;
import com.oms.bean.OrderLine;
import com.oms.components.cart.controller.CartController;
import com.oms.serverapi.MediaApi;

@RunWith(Parameterized.class)
public class TotalCostBlackBoxTest {
	
	private float totalCost;
	private float expectedResult;
	
	private Order order;
	
	ArrayList<ArrayList<OrderLine>> testList;
	
	
	@Before
	public void initialize() {
		testList = new ArrayList<ArrayList<OrderLine>>(6);
		testList[0].add(new OrderLine("", "", 0, 0, 0));
		testList[1].add(new OrderLine("", "", 1, 200000, 2));
		testList[2].add(new OrderLine("", "", 1, 300000, 4));
		testList[3].add(new OrderLine("", "", 1, 100000, 0.4));
		testList[4].add(new OrderLine("", "", 1, 400000, 1.5));
		testList[5].add(new OrderLine("", "", 1, 600000, 4));
	}
	
	
	public TotalCostBlackBoxTest(float totalCost, float expectedResult) {
		super();
		this.totalCost = totalCost;
		this.expectedResult = expectedResult;
	}
	

	public float getTotalCostFromParameter(ArrayList<OrderLine> orderLineList, String customerAddress) {
		order = new Order();
		for (OrderLine ol : orderLineList) {
			order.addOrderLine(ol);
		}
		order.setCustomerAddress(customerAddress);
		return order.getTotalCost();
	}
	
	@Parameterized.Parameters
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] { 
			{getTotalCostFromParameter(testList[0], "Tự Nhiên"), 0 },
			{getTotalCostFromParameter(testList[1], "HN"), 222000 },
			{getTotalCostFromParameter(testList[2], "HCM"), 327000 },
			{getTotalCostFromParameter(testList[3], "Thái Bình"), 130000 },
			{getTotalCostFromParameter(testList[4], "Bắc Giang"), 435000 },
			{getTotalCostFromParameter(testList[5], "Thái Nguyên"), 600000 },
			
		});
	}
	
	@Test
	public void testTotalCost() {
		
		assertEquals(expectedResult, totalCost, 500);
	}
	
}
