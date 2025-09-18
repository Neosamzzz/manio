package com.proj.manio.service.impl;

import com.proj.manio.VO.ChatOverviewVO;
import com.proj.manio.VO.MessageVO;
import com.proj.manio.mapper.MessageMapper;
import com.proj.manio.pojo.Message;
import com.proj.manio.pojo.Result;
import com.proj.manio.service.AdminChatService;
import com.proj.manio.util.JsonUtil;
import com.proj.manio.util.UserHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AdminChatServiceImpl implements AdminChatService {
    @Autowired
    private MessageMapper messageMapper;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @Override
    public List<ChatOverviewVO> getChatHistory() {
        List<ChatOverviewVO> listChat = messageMapper.getAllChat();
        return listChat;
    }

    @Transactional
    @Override
    public List<MessageVO> getHistory(Integer adminId,Integer userId) {
        // 已读
        stringRedisTemplate.opsForHash().delete("chat:unread:admin"+adminId,userId);
        return messageMapper.getChatHistory(userId);
    }

    //获取未读列表
    @Override
    public Set<String> getUnreadUsers() {
        Integer adminId = UserHolder.get();
        Map<Object, Object> entries = stringRedisTemplate.opsForHash().entries("chat:unread:admin:" + adminId);
        return entries.keySet()
                .stream()
                .map(Object::toString)
                .collect(Collectors.toSet());
    }
}
