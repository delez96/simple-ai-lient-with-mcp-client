package org.example;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AiController {

    private final ChatClient chatClient;

    private final SyncMcpToolCallbackProvider syncMcpToolCallbackProvider;
    private final VectorStore vectorStore;

    public AiController(ChatClient.Builder chatClientBuilder,
                        SyncMcpToolCallbackProvider syncMcpToolCallbackProvider,
                        VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.syncMcpToolCallbackProvider = syncMcpToolCallbackProvider;
        this.vectorStore = vectorStore;
    }

    @GetMapping("/ai")
    public String generation(String userInput) {
        return this.chatClient.prompt()
                .user(userInput)
                .toolCallbacks(syncMcpToolCallbackProvider)
                .call()
                .content();
    }

    @GetMapping("/rag")
    public String rag(String userInput) {
        return this.chatClient.prompt()
                .user(userInput)
                .advisors(new QuestionAnswerAdvisor(vectorStore))
                .call()
                .content();
    }

}
