package com.oms.totalcost.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

import org.junit.Before;
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
	
	private static Order order;
	
	private static ArrayList<OrderLine> testlist1 = new ArrayList<OrderLine>();
	private static ArrayList<OrderLine> testlist2 = new ArrayList<OrderLine>();
	private static ArrayList<OrderLine> testlist3 = new ArrayList<OrderLine>();
	private static ArrayList<OrderLine> testlist4 = new ArrayList<OrderLine>();
	private static ArrayList<OrderLine> testlist5 = new ArrayList<OrderLine>();
	private static ArrayList<OrderLine> testlist6 = new ArrayList<OrderLine>();
	
	
	
	public TotalCostBlackBoxTest(float totalCost, float expectedResult) {
		super();
		this.totalCost = totalCost;
		this.expectedResult = expectedResult;
	}
	

	public static float getTotalCostFromParameter(ArrayList<OrderLine> orderLineList, String customerAddress) {
		order = new Order();
		for (OrderLine ol : orderLineList) {
			order.addOrderLine(ol);
		}
		order.setCustomerAddress(customerAddress);
		return order.getTotalCost();
	}
	
	@Parameterized.Parameters(name = "{index}: totalCost:{0} vs expectedCost :{1}")
	public static Collection<Object[]> primeNumbers() {
		testlist1.add(new OrderLine("", "", 0, 0, 0));
		testlist2.add(new OrderLine("", "", 1, 200000, 2));
		testlist3.add(new OrderLine("", "", 1, 300000, 4));
		testlist4.add(new OrderLine("", "", 1, 100000, (float)0.4));
		testlist5.add(new OrderLine("", "", 1, 400000, (float)1.5));
		testlist6.add(new OrderLine("", "", 1, 600000, 4));
		
		return Arrays.asList(new Object[][] { 
			{getTotalCostFromParameter(testlist1, "Tự Nhiên"), 0 },
			{getTotalCostFromParameter(testlist2, "HN"), 222000 },
			{getTotalCostFromParameter(testlist3, "HCM"), 327000 },
			{getTotalCostFromParameter(testlist4, "Thái Bình"), 130000 },
			{getTotalCostFromParameter(testlist5, "Bắc Giang"), 435000 },
			{getTotalCostFromParameter(testlist6, "Thái Nguyên"), 600000 },
			
		});
	}
	
	@Test
	public void testTotalCost() {
		assertTrue("Error test", expectedResult == totalCost);
		//assertEquals(expectedResult, totalCost, 500);
	}
	
}
