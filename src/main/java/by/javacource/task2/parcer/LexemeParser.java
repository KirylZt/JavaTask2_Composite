package by.javacource.task2.parcer;

import by.javacource.task2.entity.TextComponent;
import by.javacource.task2.entity.TextComponentType;
import by.javacource.task2.entity.TextComposite;

public class LexemeParser implements ElementParser{
    private static final String LEXEME_SPLITTER_REGEX = "\\s";

    private final WordParser wordParser;

    public LexemeParser() {
        this.wordParser = new WordParser();
    }

    @Override
    public void parse(TextComponent component, String data) {
        String[] lexemes = data.split(LEXEME_SPLITTER_REGEX);

        for (String lexeme : lexemes) {
            TextComponent lexemeComponent = new TextComposite(TextComponentType.LEXEME);
            component.add(lexemeComponent);
            wordParser.parse(lexemeComponent, lexeme);
        }

    }

}
