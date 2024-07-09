package org.example;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {

    @Test
    void DSVInput1Test() throws Exception {
        String source = "src/main/resources/DSV input 1.txt";
        String target = "src/main/resources/DSV output 1.jsonl";
        String expectedOutput = "src/main/resources/JSONL output.jsonl";
        String delimeter = ",";
        String replace = "\\|";
        String replaceWith = "";
        deleteFile(target);
        App.main(new String[] {source,target,delimeter,replace,replaceWith});
        assertEquals(true,compareFilesLineByLine(target,expectedOutput));
    }

    @Test
    void DSVInput2Test() throws Exception {
        String source = "src/main/resources/DSV input 2.txt";
        String target = "src/main/resources/DSV output 2.jsonl";
        String expectedOutput = "src/main/resources/JSONL output.jsonl";
        String delimeter = "\\|";
        String replace = ",";
        String replaceWith = "";
        String replace2 = "\\|";
        String replaceWith2 = ",";

        deleteFile(target);
        App.main(new String[] {source,target,delimeter,replace,replaceWith,replace2,replaceWith2});
        assertEquals(true,compareFilesLineByLine(target,expectedOutput));
    }

    @Test
    void main_withIncompleteArgs_shouldThrowError() {
        Throwable exception = assertThrows(Exception.class, () -> {
            App.main(new String[] {});
        });
        assertEquals("Please supply sourceFile,targetFile and delimeter", exception.getMessage());

        exception = assertThrows(Exception.class, () -> {
            App.main(new String[] {"test"});
        });
        assertEquals("Please supply sourceFile,targetFile and delimeter", exception.getMessage());

        exception = assertThrows(Exception.class, () -> {
            App.main(new String[] {"test","test"});
        });
        assertEquals("Please supply sourceFile,targetFile and delimeter", exception.getMessage());

        exception = assertThrows(Exception.class, () -> {
            App.main(new String[] {"test","test","test","asd"});
        });
        assertEquals("Complete replacement args", exception.getMessage());
    }

    private static boolean compareFilesLineByLine(String file1, String file2) throws IOException {
        Path path1 = Paths.get(file1);
        Path path2 = Paths.get(file2);

        // Read all lines from both files
        return Files.readAllLines(path1).equals(Files.readAllLines(path2));
    }

    private void deleteFile(String filename){
        Path path = Paths.get(filename);
        try {
            // Check if file exists
            if (Files.exists(path)) {
                // Delete the file
                Files.delete(path);
                System.out.println("File " + filename + " has been deleted.");
            } else {
                System.out.println("File " + filename + " does not exist.");
            }
        } catch (IOException e) {
            System.err.println("Unable to delete the file: " + e.getMessage());
        }

    }
}