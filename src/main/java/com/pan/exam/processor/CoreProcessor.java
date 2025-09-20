package com.pan.exam.processor;

import com.pan.exam.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CoreProcessor {

    @Value("${char.consecutiveCount}")
    private int consecutiveCount;

    @Autowired
    private Map<String, ProcessService> processMap;

    public static final Map<Integer, String> MODE_MAPPING = Map.of(
            1, "DeleteChar",
            2, "ReplaceChar"
    );

    public String execute(String input, int mode) throws Exception{
        return processMap.get(MODE_MAPPING.get(mode)).processChar(input, consecutiveCount);
    }
}
