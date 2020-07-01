package fileParser;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import cmdParser.valueCmdLine;
import com.opencsv.*;
import org.apache.commons.lang3.ArrayUtils;

public class First_lab {
    public static void main(String[] args) {
        valueCmdLine.parseCmdLine(args);
        try {
            Stream<String> stream = Files.lines(Paths.get("input.txt"), Charset.forName("windows-1251"));
            String[] arrayWords = stream.map(e -> Pattern.compile("[^A-Za-z]+").split(e)).reduce(new String[0], ArrayUtils::addAll);
            int n = valueCmdLine.getLegthPhrase();
            int m = valueCmdLine.getfrequency();
            int count = 1;

            TreeMap<String, Integer> phrase = ngrams(n, arrayWords);

            CSVWriter writer = new CSVWriter(new FileWriter("input.csv"), ';', ICSVWriter.NO_QUOTE_CHARACTER, ICSVWriter.NO_ESCAPE_CHARACTER,"\r\n");

            phrase.forEach((x, y) -> {
                if(y >= m) {
                    writer.writeNext(new String[]{x, Integer.toString(y), Float.toString(y / (float) count * 100)});
                }
            });

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TreeMap<String, Integer> ngrams(int n, String[] words) {
        TreeMap<String, Integer> ngrams = new TreeMap<>();
        String tmp;
        for (int i = 0; i < words.length - n + 1; i++) {
            tmp = concat(words, i, i + n);
            if(ngrams.containsKey(tmp)){
                ngrams.put(tmp, ngrams.get(tmp) + 1);
            }else{
                ngrams.put(tmp, 1);
            }
        }

        return ngrams;
    }

    public static String concat(String[] words, int start, int end) {
        StringBuilder sb = new StringBuilder();

        for (int i = start; i < end; i++) {
            sb.append(i > start ? " " : "").append(words[i]);
        }

        return sb.toString();
    }
}
