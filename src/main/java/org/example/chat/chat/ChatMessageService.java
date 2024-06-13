package org.example.chat.chat;

import data.mapper.ChatMessageMapperInter;
import lombok.RequiredArgsConstructor;
import org.example.chat.chatroom.ChatRoomService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChatMessageService {
    private final ChatMessageMapperInter chatMessageMapperInter;
    private final ChatRoomService chatRoomService;

    public ChatMessageDto save(ChatMessageDto chatMessageDto){
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
//        return chatId.map(chatMessageMapperInter::findByChatId).orElse(new ArrayList<>());
    }



}
