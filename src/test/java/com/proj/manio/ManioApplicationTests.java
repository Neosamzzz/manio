package com.proj.manio;

import com.proj.manio.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManioApplicationTests {

	@Autowired
	private RedisUtil redisUtil;
	@Test
	void test() {
		System.out.println(Runtime.getRuntime().availableProcessors());

	}


}
