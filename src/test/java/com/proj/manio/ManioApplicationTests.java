package com.proj.manio;

import com.aliyuncs.exceptions.ClientException;
import com.proj.manio.util.AliyunOSS;
import com.proj.manio.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.io.IOException;

@SpringBootTest
class ManioApplicationTests {

	@Autowired
	private RedisUtil redisUtil;
    @Autowired
    private AliyunOSS aliyunOSS;

	@Test
	void test() throws ClientException, IOException {
		// The client gets the API key from the environment variable `GEMINI_API_KEY`.
		Client client = new Client();

		GenerateContentResponse response =
				client.models.generateContent(
						"gemini-2.5-flash",
						"hello",
						null);

		System.out.println(response.text());

	}


}
