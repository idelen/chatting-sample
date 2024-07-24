package com.jackpot.chatting.controller

import com.jackpot.chatting.dto.ChatRoom
import com.jackpot.chatting.repository.ChatRoomRepository
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/chat")
class ChatRoomController(
    private val chatRoomRepository: ChatRoomRepository,
) {
    @GetMapping("/room")
    fun rooms(model: Model): String {
        return "chat/room-list"
    }

    @GetMapping("/rooms")
    @ResponseBody
    fun room(): List<ChatRoom> {
        return chatRoomRepository.findAllRoom();
    }

    @PostMapping("/room")
    @ResponseBody
    fun createRoom(@RequestParam name: String): ChatRoom {
        return chatRoomRepository.createChatRoom(name)
    }

    @GetMapping("/room/enter/{roomId}")
    fun roomDetail(model: Model, @PathVariable roomId: String): String {
        model.addAttribute("roomId", roomId)
        return "/chat/roomdetail"
    }

    @GetMapping("/room/{roomId}")
    @ResponseBody
    fun roomInfo(@PathVariable roomId: String): ChatRoom? {
        return chatRoomRepository.findRoomById(roomId)
    }
}