package by.javacource.task2.parcer;

import by.javacource.task2.entity.TextComponent;
import by.javacource.task2.entity.TextComponentType;
import by.javacource.task2.entity.TextComposite;

import java.util.List;
import java.util.stream.Stream;

public class ParagraphParser implements ElementParser{
    private static final String PARAGRAPH_SPLITTER_REGEX = "(^|\\n)(\\t|\\s{4})";

    private final SentenceParser sentenceParser;

    public ParagraphParser() {
        this.sentenceParser = new SentenceParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        List<String> paragraphs = Stream.of(data.split(PARAGRAPH_SPLITTER_REGEX))
                .filter(p -> !p.isEmpty())
                .toList();

        for (String paragraph : paragraphs) {
            TextComponent paragraphComponent = new TextComposite(TextComponentType.PARAGRAPH);
            component.add(paragraphComponent);
            sentenceParser.parse(paragraphComponent, paragraph);
        }
    }
}
