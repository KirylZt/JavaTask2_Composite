package by.javacource.task2.parcer;

import by.javacource.task2.entity.Letter;
import by.javacource.task2.entity.TextComponent;

public class LetterParser implements ElementParser{

    @Override
    public void parse(TextComponent component, String data) {
        char[] letters = data.toCharArray();

        for (char letter : letters) {
            component.add(new Letter(letter));
        }
    }
}
