package org.example;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.reader.TextReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InitializingStore {

    private final VectorStore vectorStore;
    @Value("classpath:song.txt")
    private Resource textResource;

    @PostConstruct
    void initialize() {
        TextReader textReader = new TextReader(textResource);
        TokenTextSplitter tokenTextSplitter = new TokenTextSplitter();
        vectorStore.add(tokenTextSplitter.apply(textReader.get()));
    }

}
