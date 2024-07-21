package com.jackpot.chatting.dto

data class ChatMessage(
    var type: MessageType,
    var roomId: String,
    var sender: String,
    var message: String
) {
    enum class MessageType {
        ENTER, TALK
    }
}