package com.jackpot.chatting.dto

import java.util.*

data class ChatRoom(
    var roomId: String,
    var name: String,
) {
    companion object {
        fun create(name: String): ChatRoom {
            return ChatRoom(
                roomId = UUID.randomUUID().toString(),
                name = name
            )
        }
    }
}
