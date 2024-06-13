package data.chat.chatroom;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatRoomDto {
    private String id;
    private String chatId;
    private String senderId;
    private String recipientId;
}
