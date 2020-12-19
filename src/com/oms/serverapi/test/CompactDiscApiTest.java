package com.oms.serverapi.test;
import org.junit.Test;

import com.oms.bean.CompactDisc;
import com.oms.serverapi.MediaApi;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;

public class CompactDiscApiTest {
	private MediaApi api = new MediaApi();
	
	@Test
	public void testGetAllBooks() {
		HashMap<String, String> query = new HashMap<>();

		query.put("title", "Selena Gomez 2020");

		ArrayList<CompactDisc> list= api.getCds(query);
		assertEquals("Error in getCds API!", list.size(), 3);
	}
	
	@Test(timeout = 1000)
	public void testResponse() {
		api.getCds(null);
	}
}