package com.example.bai12.chat;

record ClientEvent(ChatEventType type, String sender, String content) {
}
