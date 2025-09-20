package com.pan.exam.service.Impl;

import com.pan.exam.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import java.util.stream.IntStream;

@Service("ReplaceChar")
@Slf4j
public class ReplaceConsecutiveCharServiceImpl implements ProcessService {
    @Override
    public String processChar(String input, int consecutiveCount) throws Exception {
        log.info("replace char input = {}", input);
        return replaceConsecutiveChars(input, consecutiveCount);
    }

    private String replaceConsecutiveChars(String str, int consecutiveCount) {
        StringBuilder result = new StringBuilder();
        int start = 0;

        while (start < str.length()) {
            char current = str.charAt(start);
            int end = start + (int)IntStream.range(start, str.length())
                 .takeWhile(idx -> str.charAt(idx) == current)
                 .count();
            if (end - start >= consecutiveCount) {
             char replacement = (char)(str.charAt(start) - 1);
             if (replacement >= 'a') {
                 result.append(replacement);
             }
            } else {
             result.append(str.substring(start, end));
            }
            start = end;
        }
        String compressed = result.toString();
        log.info("-> {}", compressed);
        return compressed.equals(str) ? compressed : replaceConsecutiveChars(compressed,consecutiveCount);
    }
}
