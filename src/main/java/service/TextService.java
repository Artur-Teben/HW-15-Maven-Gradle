package service;

import java.util.*;
import java.util.stream.Collectors;

public class TextService {

    public int countAllWords(List<String> songWords) {
        int count = 0;
        for (String word : songWords) {
            if (word.contains("'")) {
                count += 2;
            }
            count++;
        }
        return count;
    }

    public void printTopWords(List<String> songWords, int topLimit) {
        Map<String, Integer> topWords = new TreeMap<>();

        for (String songWord : songWords) {
            int key = (int) songWords.stream()
                    .filter(songWord::equals)
                    .count();
            topWords.put(songWord, key);
        }

        topWords.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(topLimit)
                .forEach(w -> System.out.print(w + " | "));
    }

    public List<String> removeCensoredWords(List<String> songWords, List<String> censoredWords) {
        for (String censoredWord : censoredWords) {
            songWords.removeIf(w -> w.contains(censoredWord));
        }
        return songWords;
    }

    public List<String> removeWordsBySize(List<String> songWords, int wordLength) {
        songWords.removeIf(word -> word.length() <= wordLength);
        return songWords;
    }

    public List<String> saveRemovedWords(List<String > songWords, List<String> censoredWords, int wordLength) {
        List<String> removedWords = new ArrayList<>();

        for (String censoredWord : censoredWords) {
            if (songWords.contains(censoredWord)) {
                removedWords.add(censoredWord);
            }
        }

        for (String songWord : songWords) {
            if (songWord.length() <= wordLength) {
                removedWords.add(songWord);
            }
        }
        return removedWords;
    }

    public List<String> removeRedundantSymbols(List<String> songWords, String regex) {
        return songWords.stream()
                .map(String::toLowerCase)
                .map(w -> w.replaceAll(regex, ""))
                .collect(Collectors.toList());
    }
}
