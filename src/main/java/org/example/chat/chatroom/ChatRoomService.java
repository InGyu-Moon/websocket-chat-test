package org.example.chat.chatroom;

import data.mapper.ChatRoomMapperInter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatRoomService {
    private final ChatRoomMapperInter chatRoomMapperInter;
    public String getChatRoomId(
            String senderId,
            String recipientId,
            boolean createNewRoomIfNotExists
    ) {
        ChatRoomDto dto = chatRoomMapperInter.findBySenderIdAndRecipientId(senderId, recipientId);
        if(dto == null) {
            if (createNewRoomIfNotExists) {
                dto = new ChatRoomDto();
                dto.setSenderId(senderId);
                dto.setRecipientId(recipientId);
                String chatId = createChatId(senderId, recipientId);
                return chatId;
            }
        }
        return dto.getChatId();
    }

    private String createChatId(String senderId, String recipientId) {
        var chatId = String.format("%s_%s", senderId, recipientId);

        ChatRoomDto senderRecipient = new ChatRoomDto();
        senderRecipient.setChatId(chatId);
        senderRecipient.setSenderId(senderId);
        senderRecipient.setRecipientId(recipientId);

        ChatRoomDto recipientSender = new ChatRoomDto();
        recipientSender.setChatId(chatId);
        recipientSender.setSenderId(recipientId);
        recipientSender.setRecipientId(senderId);

        chatRoomMapperInter.insertChatRoom(senderRecipient);
        chatRoomMapperInter.insertChatRoom(recipientSender);

        return chatId;
    }




}
