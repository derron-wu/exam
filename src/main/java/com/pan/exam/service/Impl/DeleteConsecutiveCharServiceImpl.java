package com.pan.exam.service.Impl;

import com.pan.exam.service.ProcessService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("DeleteChar")
@Slf4j
public class DeleteConsecutiveCharServiceImpl implements ProcessService {
    @Override
    public String processChar(String input, int consecutiveCount) throws Exception {
        log.info("delete char input = {}",input);
        return removeConsecutiveChars(input, consecutiveCount);
    }

    private String removeConsecutiveChars(String str, int consecutiveCount){
        String compressed = IntStream.range(0, str.length())
            .filter(i -> {
                char current = str.charAt(i);
                int back = i;
                int forward = i+1;
                int count = 0;
                while (back >= 0 && current == str.charAt(back)){
                    back--;
                    count++;
                }
                while (forward < str.length() && current == str.charAt(forward)){
                    forward++;
                    count++;
                }
                return count < consecutiveCount;
            })
            .mapToObj(i -> String.valueOf(str.charAt(i)))
            .collect(Collectors.joining());

        log.info("-> {}", compressed);
        return compressed.equals(str) ? compressed : removeConsecutiveChars(compressed,consecutiveCount);
    }
}
