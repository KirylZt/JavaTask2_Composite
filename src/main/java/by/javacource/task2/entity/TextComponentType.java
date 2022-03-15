package by.javacource.task2.entity;

public enum TextComponentType {

    TEXT("", ""),
    PARAGRAPH("\t", "\n"),
    SENTENCE("", ""),
    LEXEME("", " "),
    WORD("", "");

    private final String prefix;
    private final String postfix;

    private TextComponentType(String prefix, String postfix) {
        this.prefix = prefix;
        this.postfix = postfix;
    }

    public String getPrefix() {
        return prefix;
    }

    public String getPostfix() {
        return postfix;
    }
}
