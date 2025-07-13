package org.example;

import io.modelcontextprotocol.client.McpSyncClient;
import org.springframework.ai.mcp.SyncMcpToolCallbackProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class SpringAiDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringAiDemoApplication.class, args);
    }

    @Bean
    SyncMcpToolCallbackProvider syncMcpToolCallbackProvider(List<McpSyncClient> mcpSyncClientList) {
        return new SyncMcpToolCallbackProvider(mcpSyncClientList);
    }

}
