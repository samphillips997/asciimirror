package asciimirror;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("Input the file path:");
        // get filepath
        Scanner scanner = new Scanner(System.in);
        String filePath = scanner.nextLine();

        List<String> lines = addLinesToList(filePath);

        int max = getLongestElement(lines);

        printLines(lines, max);

    }

    public static StringBuilder reverseString(String input) {
        StringBuilder reversed = new StringBuilder();
        // reverse each special char in input
        for (int i = 0; i < input.length(); i++) {
            switch (input.charAt(i)) {
                case '<':
                    reversed.append('>');
                    break;
                case '>':
                    reversed.append('<');
                    break;
                case '[':
                    reversed.append(']');
                    break;
                case ']':
                    reversed.append('[');
                    break;
                case '{':
                    reversed.append('}');
                    break;
                case '}':
                    reversed.append('{');
                    break;
                case '(':
                    reversed.append(')');
                    break;
                case ')':
                    reversed.append('(');
                    break;
                case '/':
                    reversed.append('\\');
                    break;
                case '\\':
                    reversed.append('/');
                    break;
                default:
                    reversed.append(input.charAt(i));
            }
        }

        // reverse entire string which reverses chars again
        return reversed.reverse();
    }

    public static List<String> addLinesToList(String path) {
        List<String> lines = new ArrayList<>();

        // add lines to list
        try (Scanner fileScanner = new Scanner(new File(path))) {
            while (fileScanner.hasNextLine()) {
                lines.add(fileScanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        return lines;
    }

    public static int getLongestElement(List<String> lines) {
        int longest = 0;
        for (String line : lines) {
            if (line.length() > longest) {
                longest = line.length();
            }
        }

        return longest;
    }

    public static void printLines(List<String> lines, int max) {
        for (String line : lines) {
            // add padding to the line if shorter than max line length
            System.out.println(line + " ".repeat(max - line.length()) + " | " + " ".repeat(max - line.length()) + reverseString(line));
        }
    }
}