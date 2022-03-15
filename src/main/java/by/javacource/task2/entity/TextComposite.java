package by.javacource.task2.entity;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TextComposite implements TextComponent{

    static Logger logger = LogManager.getLogger();

    private TextComponentType type;
    private List<TextComponent> components = new ArrayList<TextComponent>();

    public TextComposite(TextComponentType type) {
        this.type = type;
    }

    @Override
    public boolean add(TextComponent component) {
        return components.add(component);
    }

    @Override
    public boolean remove(TextComponent component) {
        return components.remove(component);
    }

    @Override
    public List<TextComponent> getElement() {
        return new ArrayList<TextComponent>(components);
    }

    public TextComponentType getType() {
        return type;
    }

    public void setType(TextComponentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TextComposite that = (TextComposite) o;
        return type == that.type && Objects.equals(components, that.components);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, components);
    }

    @Override
    public String toString() {
        StringBuilder demonstrativeForm = new StringBuilder();

        demonstrativeForm.append(type.getPrefix());

        components.forEach(c -> demonstrativeForm.append(c.toString()));

        demonstrativeForm.append(type.getPostfix());

        return demonstrativeForm.toString();
    }
}
