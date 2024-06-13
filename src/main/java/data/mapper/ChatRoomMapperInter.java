package data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.chat.chatroom.ChatRoomDto;

import java.util.List;

@Mapper
public interface ChatRoomMapperInter {
    public ChatRoomDto findBySenderIdAndRecipientId(String senderId, String recipientId);
    public void insertChatRoom(ChatRoomDto chatRoomDto);
}
