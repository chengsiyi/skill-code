package com.chengsy.code.algorithm.dfa;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author chengsiyi
 * @date 2021/3/24 14:58
 */
public class SensitiveWordFilter {
    public static final Logger logger = LoggerFactory.getLogger(SensitiveWordFilter.class);

    private static Map<Object, Object> sensitiveWordTree;
    //最小匹配规则
    public static int minMatchType = 1;
    //最大匹配规则
    public static int maxMatchType = 2;

    public static void init(Set<String> sensitiveWords) {
        sensitiveWordTree = new HashMap<>(sensitiveWords.size());
        Map<Object, Object> exist;
        for (String word : sensitiveWords) {
            Map<Object, Object> currentOptTree = sensitiveWordTree;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                exist = (Map<Object, Object>) currentOptTree.get(c);
                if (exist == null) {
                    Map<Object, Object> node = new HashMap<>();
                    node.put("end", "0");
                    currentOptTree.put(c, node);
                    currentOptTree = node;
                } else {
                    currentOptTree = exist;
                }
                if (i == word.length() - 1) {
                    currentOptTree.put("end", "1");
                }
            }
        }
    }

    private static boolean isEnd(Map<Object, Object> node) {
        return "1".equals(node.get("end"));
    }

    public static int checkSensitive(String text, Integer matchType) {
        if (StringUtils.isBlank(text)) {
            throw new NullPointerException("text can not null");
        }
        boolean match = false;
        AtomicInteger matchCount = new AtomicInteger();
        Map<Object, Object> currentOptTree = sensitiveWordTree;
        for (int i = 0; i < text.length(); i++) {
            currentOptTree = (Map) currentOptTree.get(text.charAt(i));
            if (currentOptTree == null) {
                return 0;
            }
            matchCount.incrementAndGet();
            if (isEnd(currentOptTree)) {
                match = true;
                if (minMatchType == matchType) {
                    break;
                }
            }
        }
        if (matchCount.get() < 2 || !match) {
            matchCount.set(0);
        }
        return matchCount.get();
    }

    public static void main(String[] args) throws Exception {
        List<String> result = new ArrayList<>();
        ClassPathResource resource = new ClassPathResource("words.txt");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource.getInputStream()))) {
            String temp;
            while ((temp = reader.readLine()) != null) {
                result.add(temp);
            }
            init(new HashSet<>(result));
            Collections.shuffle(result);

            long start = System.currentTimeMillis();
            int times = 10000;
            List<String> souce = new ArrayList<>();
            result.forEach(f -> souce.add(f + RandomStringUtils.randomNumeric(0, 5)));
            for (int i = 0; i < times; i++) {
                for (String s : souce) {
                    checkSensitive(s, minMatchType);
                }
            }
            long end = System.currentTimeMillis();

            long useTime = end - start;
            int totalMatchTimes = result.size() * times;
            logger.info("匹配结束,匹配次数:{},耗时:{},qps:{}", totalMatchTimes, useTime,
                    totalMatchTimes / TimeUnit.MILLISECONDS.toSeconds(useTime));

            start = System.currentTimeMillis();
            for (int i = 0; i < times; i++) {
                for (String fuckWord : souce) {
                    if (fuckWord.toLowerCase().contains(fuckWord.toLowerCase())) {
                        int j = 0;
                    }
                }
            }
            end = System.currentTimeMillis();
            useTime = end - start;
            logger.info("匹配结束,匹配次数:{},耗时:{},qps:{}", totalMatchTimes, useTime,
                    totalMatchTimes / TimeUnit.MILLISECONDS.toSeconds(useTime));

        } catch (Exception e) {
            logger.error(">>>>>加载海底捞火锅联盟黑名单用户发生异常", e);
        }
    }
}
