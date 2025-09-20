package com.pan.exam;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
@Slf4j
class ExamMockMvcTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void mockMvcTest() {
		try {
			mockMvc.perform(MockMvcRequestBuilders.get("/get-new-string?input=aaabbbcccd&mode=1"))
					.andExpect(status().isOk())
					.andExpect(content().string("d"));

			mockMvc.perform(MockMvcRequestBuilders.get("/get-new-string?input=gggabbbaffccdddf&mode=2"))
					.andExpect(status().isOk())
					.andExpect(content().string("ebf"));

			mockMvc.perform(MockMvcRequestBuilders.get("/get-new-string?input=gggabbbaffccdddf&mode=3"))
					.andExpect(status().is4xxClientError())
					.andExpect(content().string("Invalid mode parameter ：[1->Delete consecutive char; 2->Replace consecutive char]"));

			mockMvc.perform(MockMvcRequestBuilders.get("/get-new-string?input=234234gggabbbawwwr&mode=2"))
					.andExpect(status().is4xxClientError())
					.andExpect(content().string("Invalid input parameter : Non empty lowercase letters and length less then 100"));
        }catch (Exception e){
			log.error(e.getMessage());
		}
	}
}
