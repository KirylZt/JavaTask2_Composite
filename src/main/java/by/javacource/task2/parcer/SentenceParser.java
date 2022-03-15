package by.javacource.task2.parcer;

import by.javacource.task2.entity.TextComponent;
import by.javacource.task2.entity.TextComponentType;
import by.javacource.task2.entity.TextComposite;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceParser implements ElementParser{

    private static final String SENTENCE_REGEX = "(\\p{Upper}|[А-ЯЁ]).+?(\\.|\\!|\\?|\\u2026)(\\s|$)";

    private final LexemeParser lexemeParser;

    public SentenceParser() {
        this.lexemeParser = new LexemeParser();
    }


    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(SENTENCE_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String sentence = matcher.group();
            TextComponent sentenceComponent = new TextComposite(TextComponentType.SENTENCE);
            component.add(sentenceComponent);
            lexemeParser.parse(sentenceComponent, sentence);
        }
    }
}
