package by.javacource.task2.service.impl;

import by.javacource.task2.entity.Punctuation;
import by.javacource.task2.entity.TextComponent;
import by.javacource.task2.entity.TextComponentType;
import by.javacource.task2.entity.TextComposite;
import by.javacource.task2.exception.ApplicationException;
import by.javacource.task2.service.TextService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.OptionalInt;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TextServiceImpl implements TextService {

    static Logger logger = LogManager.getLogger();

    private static final String VOWEL_REGEX = "(?iu)[aeiouyаеёионыэюя]";
    private static final String CONSONANT_REGEX = "(?iu)[a-zа-я&&[^aeiouyаеёионыэюя]]";

    @Override
    public List<TextComponent> sortParagraphsByNumberOfSentences(TextComponent text) throws ApplicationException {
        if (!validateParameter(text)) {
            throw new ApplicationException("Unable type of TextComponent.");
        }

        List<TextComponent> paragraphs = text.getElement();

        paragraphs.sort(new Comparator<TextComponent>() {
            public int compare(TextComponent one, TextComponent other) {
                Integer sizeOne = one.getElement().size();
                Integer sizeOther = other.getElement().size();
                return sizeOne.compareTo(sizeOther);
            }
        });

        return paragraphs;
    }

    @Override
    public List<TextComponent> findSentencesWithLongestWorld(TextComponent text) throws ApplicationException {
        if (!validateParameter(text)) {
            throw new ApplicationException("Unable type of TextComponent.");
        }

        List<TextComponent> allSentences = text.getElement().stream()
                .flatMap(p -> p.getElement().stream())
                .collect(Collectors.toList());

        OptionalInt maxLenghtOpt = allSentences.stream()
                .flatMap(s -> s.getElement().stream())
                .flatMap(l -> l.getElement().stream())
                .filter(w -> !(w instanceof Punctuation))
                .mapToInt(w -> w.getElement().size())
                .max();

        maxLenghtOpt.orElseThrow(() -> new ApplicationException("Unable lenght of word."));
        int maxLength = maxLenghtOpt.getAsInt();

        // find sentences with words, which have max length

        return allSentences.stream()
                .filter(s -> s.getElement().stream()
                        .anyMatch(l -> l.getElement().stream()
                                .filter(w -> !(w instanceof Punctuation))
                                .anyMatch(w -> w.getElement().size() == maxLength)))
                .peek(e -> logger.info("sentence to collect >> " + e))
                .collect(Collectors.toList());
    }

    @Override
    public void removeSentencesWithWordsLessThan(TextComponent text, int min) throws ApplicationException {
        if (!validateParameter(text)) {
            throw new ApplicationException("Unable type of TextComponent.");
        }

        for (TextComponent paragraph : text.getElement()) {
            for (TextComponent sentence : paragraph.getElement()) {
                int numberOfWord = 0;
                for (TextComponent lexeme : sentence.getElement()) {
                    if (!(lexeme instanceof Punctuation)) {
                        numberOfWord++;
                    }
                }
                if (numberOfWord < min) {
                    paragraph.remove(sentence);
                }
            }
        }
    }

    @Override
    public Map<String, Integer> findAndCountSameWords(TextComponent text) throws ApplicationException {
        if (!validateParameter(text)) {
            throw new ApplicationException("Unable type of TextComponent.");
        }

        Map<String, Integer> sameWords = text.getElement().stream()
                .flatMap(p -> p.getElement().stream())
                .flatMap(s -> s.getElement().stream())
                .flatMap(lx -> lx.getElement().stream())
                .filter(w -> !(w instanceof Punctuation))
                .map(w -> w.toString().toLowerCase())
                .collect(Collectors.toMap(str -> str, i -> 1, Integer::sum));

        sameWords.values().removeIf(i -> i == 1);

        return sameWords;
    }

    @Override
    public long countConsonant(TextComponent text) throws ApplicationException {
        if (!validateParameter(text)) {
            throw new ApplicationException("Unable type of TextComponent.");
        }

        Pattern pattern = Pattern.compile(CONSONANT_REGEX);

        return text.getElement().stream()
                .flatMap(p -> p.getElement().stream())
                .flatMap(s -> s.getElement().stream())
                .flatMap(lx -> lx.getElement().stream())
                .filter(w -> !(w instanceof Punctuation))
                .flatMap(w -> w.getElement().stream())
                .map(Object::toString)
                .filter(l -> pattern.matcher(l).matches())
                .count();
    }

    @Override
    public long countVowel(TextComponent text) throws ApplicationException {
        if (!validateParameter(text)) {
            throw new ApplicationException("Unable type of TextComponent.");
        }

        Pattern pattern = Pattern.compile(VOWEL_REGEX);

        return text.getElement().stream()
                .flatMap(p -> p.getElement().stream())
                .flatMap(s -> s.getElement().stream())
                .flatMap(lx -> lx.getElement().stream())
                .filter(w -> !(w instanceof Punctuation))
                .flatMap(w -> w.getElement().stream())
                .map(Object::toString)
                .filter(l -> pattern.matcher(l).matches())
                .count();
    }

    private boolean validateParameter(TextComponent component) {

        if (!(component instanceof TextComposite composite)) {
            return false;
        }

        return composite.getType() == TextComponentType.TEXT;
    }
}
