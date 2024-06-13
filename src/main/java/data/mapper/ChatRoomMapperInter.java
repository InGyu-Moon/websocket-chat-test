package data.mapper;

import org.apache.ibatis.annotations.Mapper;
import data.chat.chatroom.ChatRoomDto;


@Mapper
public interface ChatRoomMapperInter {
    public ChatRoomDto findBySenderIdAndRecipientId(String senderId, String recipientId);
    public void insertChatRoom(ChatRoomDto chatRoomDto);
}
