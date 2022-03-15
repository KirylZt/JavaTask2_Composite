package by.javacource.task2.parcer;

import by.javacource.task2.entity.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordParser implements ElementParser{

    private static final String WORD_REGEX = "[\\wа-яА-ЯёЁ]+";
    private static final String WORD_OR_PUNCTUATION_REGEX = "([\\wа-яА-ЯёЁ]+)|([\\p{Punct}\u2026])";

    private final LetterParser letterParser;

    public WordParser() {
        this.letterParser = new LetterParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        Pattern pattern = Pattern.compile(WORD_OR_PUNCTUATION_REGEX);
        Matcher matcher = pattern.matcher(data);

        while (matcher.find()) {
            String group = matcher.group();

            Pattern wordPattern = Pattern.compile(WORD_REGEX);
            Matcher wordMatcher = wordPattern.matcher(group);

            if (wordMatcher.matches()) {
                TextComponent wordComponent = new TextComposite(TextComponentType.WORD);
                component.add(wordComponent);
                letterParser.parse(wordComponent, group);
            } else {
                TextComponent punctuationComponent = new Punctuation(group.charAt(0));
                component.add(punctuationComponent);
            }
        }
    }
}
