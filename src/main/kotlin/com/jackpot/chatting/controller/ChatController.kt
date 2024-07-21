package com.jackpot.chatting.controller

import com.jackpot.chatting.dto.ChatRoom
import com.jackpot.chatting.service.ChatService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chat")
class ChatController(
    private val chatService: ChatService,
) {
    @GetMapping
    fun findAllRoom(): List<ChatRoom> {
        return chatService.findAllRoom()
    }

    @PostMapping
    fun createRoom(@RequestParam name: String): ChatRoom {
        return chatService.createRoom(name)
    }
}