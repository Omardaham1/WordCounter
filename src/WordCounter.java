import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

public class WordCounter {

    public static void main(String[] args) throws IOException {
        String inputFile = "input.txt";
        String outputFile = "output.txt";
        countWords(inputFile, outputFile);
        System.out.println("Word count written to " + outputFile);
    }

    public static void countWords(String inputFile, String outputFile) throws IOException {
        Map<String, Integer> wordFrequencies = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String word;
            while ((word = reader.readLine()) != null) {
                word = word.toLowerCase();
                wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
            }
        }

        try (FileWriter writer = new FileWriter(outputFile)) {
            wordFrequencies.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey())
                    .forEach(entry -> {
                        try {
                            writer.write(entry.getKey() + " " + entry.getValue() + "\n");
                        } catch (IOException e) {
                            System.err.println("Error writing to output file: " + e.getMessage());
                        }
                    });
        }
    }
}