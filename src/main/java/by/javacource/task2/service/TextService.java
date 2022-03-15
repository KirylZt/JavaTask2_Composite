package by.javacource.task2.service;

import by.javacource.task2.entity.TextComponent;
import by.javacource.task2.exception.ApplicationException;

import java.util.List;
import java.util.Map;

public interface TextService {

    List<TextComponent> sortParagraphsByNumberOfSentences(TextComponent text) throws ApplicationException;

    List<TextComponent> findSentencesWithLongestWorld(TextComponent text) throws ApplicationException;

    void removeSentencesWithWordsLessThan(TextComponent text, int min) throws ApplicationException;

    Map<String, Integer> findAndCountSameWords(TextComponent text) throws ApplicationException;

    long countConsonant(TextComponent text) throws ApplicationException;

    long countVowel(TextComponent text) throws ApplicationException;
}
