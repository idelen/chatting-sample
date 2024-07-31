package com.jackpot.chatting.controller

import com.jackpot.chatting.dto.ChatMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/chat")
class ChatController(
    private val simpMessageSendingOperations: SimpMessageSendingOperations
) {

    @MessageMapping("/chat/message")
    fun message(chatMessage: ChatMessage) {
        if (ChatMessage.MessageType.ENTER.equals(chatMessage.type)) {
            chatMessage.message = chatMessage.sender + "님이 입장하셨습니다."
        }
        simpMessageSendingOperations.convertAndSend("/sub/chat/room/" + chatMessage.roomId, chatMessage)
    }
}