package executor;

import reader.FileReader;
import service.TextService;

import java.util.List;

public class Executor {
    private static final String DATA_PATH = "data.txt";
    private static final String CENSORED_WORDS_PATH = "censored_words.txt";
    private static final String REGEX = "[.|,()!]";
    private static final Integer TOP_LIMIT = 5;
    private static final Integer WORD_LENGTH = 3;

    private final FileReader fileReader = new FileReader();
    private final TextService textService = new TextService();

    public void run() {

        List<String> songWords = fileReader.readFile(DATA_PATH);
        List<String> censoredWords = fileReader.readFile(CENSORED_WORDS_PATH);

        System.out.println("All song text without correcting:");
        System.out.println(songWords);

        System.out.println("\nSong text in lower case and without redundant symbols:");
        List<String> songWordsWithoutSymb = textService.removeRedundantSymbols(songWords, REGEX);
        System.out.println(songWordsWithoutSymb);
        System.out.println("Amount of words in song: " + textService.countAllWords(songWordsWithoutSymb));

        System.out.println("\nSong text without censored words:");
        List<String> censoredSongText = textService.removeCensoredWords(songWordsWithoutSymb, censoredWords);
        System.out.println(censoredSongText);
        System.out.println("Amount of words in song after censoring: " + textService.countAllWords(censoredSongText));

        System.out.println("\nTop " + TOP_LIMIT + " words in the song: ");
        textService.printTopWords(censoredSongText, TOP_LIMIT);

        List<String> songWordsAfterRemovingShortWords = textService.removeWordsBySize(censoredSongText, WORD_LENGTH);
        System.out.println("\n\nSong text after removing short words:");
        System.out.println(songWordsAfterRemovingShortWords);
        System.out.print("Amount of words in song after removing short words: ");
        System.out.println(textService.countAllWords(songWordsAfterRemovingShortWords));

        System.out.println("\nAll removed words: ");
        List<String> removedWords = textService.saveRemovedWords(songWords, censoredWords, WORD_LENGTH);
        System.out.println(removedWords);
        System.out.print("Amount of removed words: " + textService.countAllWords(removedWords));
    }
}
