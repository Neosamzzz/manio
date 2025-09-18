package com.proj.manio.interceptor;

import com.proj.manio.VO.MessageVO;
import com.proj.manio.mapper.MessageMapper;
import com.proj.manio.pojo.Admin;
import com.proj.manio.pojo.Message;
import com.proj.manio.util.IdUtil;
import com.proj.manio.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {


    // 保存所有连接的会话
    private static final Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private MessageMapper messageMapper;

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        Integer userId = (Integer) session.getAttributes().get("userId");
        String identityType = (String) session.getAttributes().get("identityType");

        sessions.put(identityType+userId, session);
        System.out.println("新连接: " + identityType+userId);
    }

    @Transactional
    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String json = message.getPayload();
        MessageVO mVO = JsonUtil.fromJson(json, MessageVO.class);// VO转换


        Message m = new Message();// 插入数据库Message对象
        Integer USER_ID = mVO.getUserId();// 用户id
        //用时间加上id拼成消息id
        String NOW = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        Long MESSAGE_ID = Long.valueOf(NOW.substring(0,6)+IdUtil.generateUserId(NOW+USER_ID));
        String MESSAGE_TEXT = mVO.getMessageText();//Message内容

        m.setId(MESSAGE_ID);
        m.setUserId(USER_ID);
        m.setDirection(mVO.getDirection());
        m.setCreateTime(LocalDateTime.now());
        m.setMessageText(MESSAGE_TEXT);

        switch (mVO.getDirection()) {//查看方向
            case 0:// 发送到管理员

                log.info("群发到管理员");
                List<Admin> allAdmin = messageMapper.getAllAdmin();
                for(Admin a : allAdmin){
                    stringRedisTemplate.opsForHash().put("chat:unread:admin:"+a.getId(),String.valueOf(USER_ID),"0");//先插入redis做缓存未读消息
                }
                log.info("插入数据库");
                messageMapper.addMessage(m);// 插入数据库
                // 发送消息
                for (WebSocketSession s : sessions.values()) {
                    if (s.isOpen() && s.getAttributes().get("identityType").equals("admin")) {
                        m.setDirection(0);
                        s.sendMessage(new TextMessage(json));
                    }
                }
                break;
            case 1: // 发送到用户
                m.setAdminId((Integer)session.getAttributes().get("userId"));
//                        stringRedisTemplate.opsForList().rightPush("TOuserId:"+USER_ID,NOW,JsonUtil.toJson(m));
                WebSocketSession userSession = sessions.get(USER_ID);
                messageMapper.addMessage(m);// 插入数据库
                if (userSession != null && userSession.isOpen()) {
                    userSession.sendMessage(new TextMessage(JsonUtil.toJson(mVO)));
                }
                break;
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessions.remove(session.getId());
        System.out.println("断开连接: " + session.getId());
    }
}