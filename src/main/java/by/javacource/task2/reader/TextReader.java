package by.javacource.task2.reader;

import by.javacource.task2.exception.ApplicationException;

public interface TextReader {

    String read(String pathToFile) throws ApplicationException;

}
