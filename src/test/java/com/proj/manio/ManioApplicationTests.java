package com.proj.manio;

import com.aliyuncs.exceptions.ClientException;
import com.proj.manio.util.AliyunOSS;
import com.proj.manio.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

@SpringBootTest
class ManioApplicationTests {

	@Autowired
	private RedisUtil redisUtil;
    @Autowired
    private AliyunOSS aliyunOSS;

	@Test
	void test() throws ClientException, IOException {
		aliyunOSS.remove("converted_让我看看.png");

	}


}
