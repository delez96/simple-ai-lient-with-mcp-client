package org.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    private final ChatClient chatClient;

    private final ChatMemory chatMemory;
    private final SyncMcpToolCallbackProvider syncMcpToolCallbackProvider;

    public AiController(ChatClient.Builder chatClientBuilder,
                        ChatMemory chatMemory,
                        SyncMcpToolCallbackProvider syncMcpToolCallbackProvider) {
        this.chatClient = chatClientBuilder.build();
        this.chatMemory = chatMemory;
        this.syncMcpToolCallbackProvider = syncMcpToolCallbackProvider;
    }

    @GetMapping("/ai")
    public String generation(String userInput) {
        return this.chatClient.prompt()
                .user(userInput)
                .advisors(MessageChatMemoryAdvisor.builder(chatMemory).build())
                .toolCallbacks(syncMcpToolCallbackProvider)
                .call()
                .content();
    }

}
