package data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.example.chat.chat.ChatMessageDto;
import org.example.chat.chatroom.ChatRoomDto;

import java.util.List;

@Mapper
public interface ChatMessageMapperInter {
    void insertChatMessage(ChatMessageDto chatMessageDto);
    List<ChatMessageDto> findByChatId (String chatId);
}
