package by.javacource.task2.entity;

import java.util.List;

public interface TextComponent {

    boolean add(TextComponent component);

    boolean remove(TextComponent component);

    List<TextComponent> getElement();

}
