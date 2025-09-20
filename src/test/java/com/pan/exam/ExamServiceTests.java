package com.pan.exam;

import com.pan.exam.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;


@SpringBootTest
@Slf4j
class ExamServiceTests {

	@Autowired
	@Qualifier("DeleteChar")
	private ProcessService deleteCharService;

	@Autowired
	@Qualifier("ReplaceChar")
	private ProcessService replaceCharService;

	@Value("${char.consecutiveCount}")
	private int consecutiveCount;

	//@MockitoBean

	@Test
	void deleteChar(){
		try{
			String case1 = deleteCharService.processChar("aaabbbcccd", consecutiveCount);
			String case2 = deleteCharService.processChar("aabcccbbad", consecutiveCount);
			String case3 = deleteCharService.processChar("abbccdddcbbaddddaaa", consecutiveCount);
			String case4 = deleteCharService.processChar("abbccdddcbbaddddaa", consecutiveCount);
			assertEquals("fail", "d",case1);
			assertEquals("fail", "d",case2);
			assertEquals("fail", "aa",case3);
			assertEquals("fail", "a",case4);
		}catch (Exception e){
			log.error("Delete Char testcase -> {}",e.getMessage());
		}
	}

	@Test
	void replaceChar(){
		try{
			String case1 = replaceCharService.processChar("abcccbad", 3);
			String case2 = replaceCharService.processChar("aabcccbbad", 3);
			String case3 = replaceCharService.processChar("abbccdddcbbaaaa", 3);
			String case4 = replaceCharService.processChar("abbccdddcbbaddddaa", 3);
			assertEquals("fail", "d",case1);
			assertEquals("fail", "d",case2);
			assertEquals("fail", "aa",case3);
			assertEquals("fail", "caa",case4);
		}catch (Exception e){
			log.error("Replace Char testcase -> {}",e.getMessage());
		}
	}
}
