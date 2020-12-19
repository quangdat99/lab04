package com.oms.optional.test;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.oms.bean.Book;
import com.oms.serverapi.MediaApi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

@RunWith(Parameterized.class)
public class DescriptionSearchTest {
	private String bookDescription;
	private String expectedResult;
	
	private MediaApi api = new MediaApi();
	
	
	public DescriptionSearchTest(String bookDescription, String expectedResult) {
		super();
		this.bookDescription = bookDescription;
		this.expectedResult = expectedResult;
	}
	

	@Parameterized.Parameters(name = "{index}: description:{0} vs expectedResult :{1}")
	public static Collection<Object[]> primeNumbers() {
		return Arrays.asList(new Object[][] { 
			{ "C#", "C# Programming" },
			{ "Java", "Java Programming" }, 
			{ "Python", "Python Programming" }
		});
	}
	
	@Test
	public void testSearchDescription() {
		HashMap<String, String> params = new HashMap<String, String>();
		params.put("description", bookDescription);
		
		ArrayList<Book> list= api.getBooks(params);
		assertTrue("No data", list.size() > 0);
		
		
		Book book = list.get(0);
		assertEquals("Eror in getBooks API!", book.getDescription(), expectedResult);
	}
}