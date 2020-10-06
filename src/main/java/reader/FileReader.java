package reader;

import lombok.SneakyThrows;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class FileReader {

    @SneakyThrows
    public List<String> readFile(String fileName) {
        File file = loadFileFromResource(fileName);
        List<String> songWords = new ArrayList<>();
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            songWords.add(scanner.next());
        }

        scanner.close();
        return songWords;
    }

    public File loadFileFromResource(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }
}
