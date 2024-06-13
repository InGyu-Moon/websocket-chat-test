package data.chat.chat;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ChatController {

    private final SimpMessagingTemplate messagingTemplate;
    private final ChatMessageService chatMessageService;

    @MessageMapping("/chat")
    public void processMessage(
            @Payload ChatMessageDto chatMessageDto
    ){


        System.out.println("chatMessageDto.toString() = " + chatMessageDto.toString());

        ChatMessageDto savedMsg = chatMessageService.save(chatMessageDto);

        System.out.println("savedMsg.toString() = " + savedMsg.toString());
        
        
        
        messagingTemplate.convertAndSendToUser(
                chatMessageDto.getRecipientId(), "/queue/messages", savedMsg
//                ChatNotification.builder()
////                        .id(savedMsg.getId())
//                        .senderId(savedMsg.getSenderId())
//                        .recipientId(savedMsg.getRecipientId())
//                        .content(savedMsg.getContent())
//                        .build()
        );

    }

    @GetMapping("/messages/{senderId}/{recipientId}")
    public ResponseEntity<List<ChatMessageDto>> findChatMessages(
            @PathVariable("senderId") String senderId,
            @PathVariable("recipientId") String recipientId){
        return ResponseEntity.ok(chatMessageService.findChatMessages(senderId, recipientId));
    }
}
