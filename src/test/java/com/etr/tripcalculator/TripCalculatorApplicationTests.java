package com.etr.tripcalculator;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class TripCalculatorApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Test
	public void requestTripCostAndDistance() throws Exception {
		mvc.perform(post("/trip")
						.contentType(MediaType.APPLICATION_JSON)

						.content("{\n" +
								"                        \"source\": \"Salem Road\",\n" +
								"                        \"destination\": \"QEW\"\n" +
								"                    }")
						.header("Authorization", ""))
						.andExpect(status().isOk())
						.andExpect(jsonPath("$.distance").value(new Double(107.96400000000001)))
						.andExpect(jsonPath("$.cost").value(new Double(26.991000000000003)));

	}

}
