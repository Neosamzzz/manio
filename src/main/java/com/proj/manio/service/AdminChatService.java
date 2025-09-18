package com.proj.manio.service;

import com.proj.manio.VO.ChatOverviewVO;
import com.proj.manio.VO.MessageVO;
import com.proj.manio.pojo.Result;

import java.util.List;
import java.util.Set;

public interface AdminChatService {
    List<ChatOverviewVO> getChatHistory();

    List<MessageVO> getHistory(Integer adminId,Integer userId);

    Set<Integer> getUnreadUsers();
}
