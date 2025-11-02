package com.proj.manio;

import com.aliyuncs.exceptions.ClientException;
import com.google.genai.types.Content;
import com.google.genai.types.GenerateContentConfig;
import com.google.genai.types.Schema;
import com.proj.manio.util.AliyunOSS;
import com.proj.manio.util.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.google.genai.Client;
import com.google.genai.types.GenerateContentResponse;

import java.io.IOException;
import java.util.Map;

@SpringBootTest
class ManioApplicationTests {

	@Autowired
	private RedisUtil redisUtil;
    @Autowired
    private AliyunOSS aliyunOSS;

	@Test
	void test() throws ClientException, IOException {
//		// 创建客户端
//		Client client = new Client();
//
//		// 第一个 Agent 输入：请求生成食谱列表
//		String requestText = "列出一些受欢迎的饼干食谱，并包括每个食谱的材料和数量。";
//
//		// 定义第一个 Agent 的身份（系统指令）
//		String agent1SystemInstruction = "你是食谱生成器，请列出流行的饼干食谱，并包括每个食谱的所有必需材料和数量。";
//
//		// 示例 JSON 字符串，代表一个 Content 对象
//		String jsonString = "{ \"parts\": [{ \"text\": \"列出一些受欢迎的饼干食谱，并包括每个食谱的材料和数量。\" }], \"role\": \"user\" }";
//
//		// 定义 JSON 输出架构
//		Schema responseSchema = Schema.builder()
//				.type(Schema.Type.ARRAY)
//				.items(Schema.builder()
//						.type(Schema.Type.OBJECT)
//						.properties(Map.of(
//								"recipeName", Schema.builder().type(Schema.Type.STRING).build(),
//								"ingredients", Schema.builder().type(Schema.Type.ARRAY).items(Schema.builder().type(Schema.Type.STRING).build()).build()
//						))
//						.build())
//				.build();
//
//		// 设置生成配置（食谱生成器的配置）
//		GenerateContentConfig config = GenerateContentConfig.builder()
//				.responseMimeType("application/json")  // 输出格式为 JSON
//				.responseSchema(responseSchema)        // JSON 输出架构
//				.systemInstruction(Content.fromJson(jsonString)) // 为第一个 Agent 设置系统指令
//				.build();
//
//		// 创建 Content 对象
//		Content content = Content.fromJson(jsonString);
//
//		// 调用 API 获取响应
//		GenerateContentResponse response = client.models.generateContent(
//				"gemini-2.0-flash",  // 使用的模型
//				content,             // 请求内容
//				config               // 配置
//		);
//
//		// 输出生成的 JSON 格式内容（第一个 Agent 生成的食谱）
//		String generatedJson = response.text();
//		System.out.println("生成的食谱列表: " + generatedJson);
//
//		// 传递给第二个 Agent 进行评分处理
//		processRecipes(generatedJson);
//	}
//
//	// 第二个 Agent：食谱评分师
//	public static void processRecipes(String jsonInput) {
//		// 假设 jsonInput 是从第一个 Agent 获取的食谱列表
//		// 第二个 Agent 对每个食谱进行评分
//		String agent2SystemInstruction = "你是食谱评分师，根据材料给每个食谱打分。";
//
//		// 模拟评分处理
//		String ratedJson = jsonInput.replace("巧克力曲奇", "巧克力曲奇，评分：A+")
//				.replace("花生酱曲奇", "花生酱曲奇，评分：A");
//
//		// 输出评分后的食谱数据
//		System.out.println("评分后的食谱: " + ratedJson);

	}


}
