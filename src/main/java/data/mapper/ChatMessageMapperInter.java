package data.mapper;

import org.apache.ibatis.annotations.Mapper;
import data.chat.chat.ChatMessageDto;

import java.util.List;

@Mapper
public interface ChatMessageMapperInter {
    void insertChatMessage(ChatMessageDto chatMessageDto);
    List<ChatMessageDto> findByChatId (String chatId);
}
