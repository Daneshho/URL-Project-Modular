package com.avihang.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCleaner {

    public static String clean(String text) {
        Matcher bodyMatcher = Pattern.compile("(?is)<body.*?>(.*?)</body>").matcher(text);
        if (bodyMatcher.find()) {
            text = bodyMatcher.group(1);
        }

        text = text.replaceAll("(?is)<(script|style|noscript).*?>.*?</\\1>", "");
        text = text.replaceAll("(?i)\\s+(alt|title|content|aria-label|data-[a-z0-9_-]+)\\s*=\\s*\"[^\"]*\"", "");
        text = text.replaceAll("(?i)\\s+(alt|title|content|aria-label|data-[a-z0-9_-]+)\\s*=\\s*'[^']*'", "");
        text = text.replaceAll("<[^>]+>", " ");
        text = text.replaceAll("&nbsp;", " ");
        text = text.replaceAll("&amp;", "&");
        text = text.replaceAll("&lt;", "<");
        text = text.replaceAll("&gt;", ">");
        text = text.replaceAll("&quot;", "\"");
        text = text.replaceAll("&#39;", "'");
        return text.replaceAll("\\s+", " ").trim();
    }
}
