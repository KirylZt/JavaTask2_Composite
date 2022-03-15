package by.javacource.task2.reader.impl;

import by.javacource.task2.exception.ApplicationException;
import by.javacource.task2.reader.TextReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TextReaderImpl implements TextReader {

    static Logger logger = LogManager.getLogger();


    @Override
    public String read(String pathToFile) throws ApplicationException {

        Path path = Paths.get(pathToFile);

        if (!Files.exists(path)) {
            logger.error("File " + pathToFile + " does not exist or is not available.");
            throw new ApplicationException("File " + pathToFile + " does not exist or is not available.");
        }


        logger.info("Reading start.");

        String text;

        try {
            text = Files.readString(path);
        } catch (IOException e) {
            logger.error("IO Exception during working with file " + pathToFile);
            throw new ApplicationException("IO Exception during working with file " + pathToFile, e);
        }

        logger.info("Reading finish.");

        return text;
    }


}
