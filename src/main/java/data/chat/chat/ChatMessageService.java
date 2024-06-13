package data.chat.chat;

import data.mapper.ChatMessageMapperInter;
import data.chat.chatroom.ChatRoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatMessageService {
    private final ChatMessageMapperInter chatMessageMapperInter;
    private final ChatRoomService chatRoomService;

    public ChatMessageDto save(ChatMessageDto chatMessageDto){

        System.out.println("ChatMessage Save");

        String chatId = chatRoomService.getChatRoomId(
                chatMessageDto.getSenderId(), chatMessageDto.getRecipientId(), true);

        chatMessageDto.setChatId(chatId);

        chatMessageMapperInter.insertChatMessage(chatMessageDto);


        return chatMessageDto;
    }

    public List<ChatMessageDto> findChatMessages(String senderId, String recipientId){
        var chatId = chatRoomService.getChatRoomId(senderId, recipientId, false);
        if (chatId != null) {
            return chatMessageMapperInter.findByChatId(chatId);
        } else {
            return new ArrayList<>();
        }
    }



}
