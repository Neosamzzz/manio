package com.proj.manio.controller.user;

import com.proj.manio.VO.MessageVO;
import com.proj.manio.mapper.MessageMapper;
import com.proj.manio.pojo.Message;
import com.proj.manio.pojo.Result;
import com.proj.manio.util.UserHolder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/userChat")
@Tag(name = "用户聊天")
public class UserChatController {
    @Autowired
    private MessageMapper messageMapper;

    @Operation(summary = "获取用户聊天历史")
    @GetMapping
    public Result<List<MessageVO>> getChatList(){
        return Result.success(messageMapper.getChatHistory(UserHolder.get()));
    }
}
