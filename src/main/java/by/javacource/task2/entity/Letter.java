package by.javacource.task2.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Objects;

public class Letter implements TextComponent{

    static Logger logger = LogManager.getLogger();

    private final char letter;

    public Letter(char letter) {
        this.letter = letter;
    }

    @Override
    public boolean add(TextComponent component) {
        logger.error("Not available operation to this component");
        throw new UnsupportedOperationException("Not available operation to this component");
    }

    @Override
    public boolean remove(TextComponent component) {
        logger.error("Not available operation to this component");
        throw new UnsupportedOperationException("Not available operation to this component");
    }

    @Override
    public List<TextComponent> getElement() {
        logger.error("Not available operation to this component");
        throw new UnsupportedOperationException("Not available operation to this component");
    }

    @Override
    public String toString() {
        return "Letter{" +
                "letter=" + letter +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Letter letter1 = (Letter) o;
        return letter == letter1.letter;
    }

    @Override
    public int hashCode() {
        return Objects.hash(letter);
    }
}
