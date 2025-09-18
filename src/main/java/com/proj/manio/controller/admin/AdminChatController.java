package com.proj.manio.controller.admin;

import com.proj.manio.VO.ChatOverviewVO;
import com.proj.manio.VO.MessageVO;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.impl.AdminChatServiceImpl;
import com.proj.manio.util.UserHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Tag(name = "客服对话")
@RestController
@RequestMapping("/manage/chat")
public class AdminChatController {

    @Autowired
    private AdminChatServiceImpl adminChatServiceImpl;

    @Operation(summary = "获取历史列表")
    @GetMapping
    public Result<List<ChatOverviewVO>> getChatHistory(){
        List<ChatOverviewVO> allChat = adminChatServiceImpl.getChatHistory();
        return Result.success(allChat);
    }
    /**
     * 返回未读用户列表
     */
    @Operation(summary = "获取未读列表")
    @GetMapping("/unread")
    public Result<Set<String>> getUnreadUsers() {
        return Result.success(adminChatServiceImpl.getUnreadUsers());
    }

    /**
     * 拉取某个用户的消息历史
     */
    @Operation(summary = "获取单个用户消息历史并建立webSocket链接")
    @GetMapping("/{userId}")
    public Result<List<MessageVO>> getHistory(@PathVariable Integer userId) {
        Integer adminId = UserHolder.get();
        return Result.success(adminChatServiceImpl.getHistory(adminId,userId));
    }

}
