package com.proj.manio.mapper;

import com.proj.manio.VO.ChatOverviewVO;
import com.proj.manio.VO.MessageVO;
import com.proj.manio.pojo.Admin;
import com.proj.manio.pojo.Message;
import com.proj.manio.pojo.Result;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MessageMapper {
    @Select("SELECT m.user_id AS userId,m.create_time AS createTime,m.message_text AS messageText FROM message m INNER JOIN (SELECT user_id, MAX(create_time) AS max_time FROM message GROUP BY user_id) t ON m.user_id = t.user_id AND m.create_time = t.max_time ORDER BY m.create_time DESC")
    List<ChatOverviewVO> getAllChat();

    @Select("SELECT * FROM message WHERE user_id = #{id} ORDER BY create_time ")
    List<MessageVO> getChatHistory(Integer id);


    @Select("SELECT * FROM admin")
    List<Admin> getAllAdmin();

    @Insert("INSERT INTO message(user_id, admin_id, create_time, message_text, direction) VALUES (#{userId},#{adminId},#{createTime},#{messageText},#{direction})")
    void addMessage(Message m);


}
