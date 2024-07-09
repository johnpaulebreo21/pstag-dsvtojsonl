package org.example.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Stream;

import static org.example.utils.StringUtil.isNumeric;
import static org.example.utils.StringUtil.splitStrings;

public class ConverterService {

    public static String[] headers;
    public void convertToJsonL(String source, String target, String delimiter,
                               ArrayList<String[]> replaceStrings) {

        try (Stream<String> lines = Files.lines(Paths.get(source))) {
             // Process each line using Stream operations
            AtomicBoolean isHeader = new AtomicBoolean(true);
            lines.forEach((line)->{
                if(isHeader.get()){
                    headers = line.split(delimiter);
                    isHeader.set(false);
                }else{
                    List<String> values = splitStrings(line,delimiter,replaceStrings);
                    //output
                    writeToFile(makeJsonL(headers,values),target);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void writeToFile(String linesToAdd, String outputFile){
        // Append lines to file using Stream API
        try (Stream<String> stream = Stream.of(linesToAdd)) {
            Path path = Paths.get(outputFile);

            // Check if file exists
            if (!Files.exists(path)) {
                Files.write(path, (Iterable<String>) stream::iterator, StandardOpenOption.CREATE_NEW);
            } else {
                Files.write(path, (Iterable<String>) stream::iterator, StandardOpenOption.APPEND);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    
    private String makeJsonL(String[] headers,List<String> values){
        StringBuilder json = new StringBuilder("{");

         for(int x = 0; x<headers.length;x++){
             String value = values.get(x);
             if(!value.isEmpty()){
                 json.append("\"" + headers[x] + "\": ");
                 if(isNumeric(value)){
                     json.append( value + ",");
                 }else{
                     json.append("\"" + value.trim() + "\",");
                 }
             }
         }
        json.deleteCharAt(json.length() - 1);
        json.append("}");
        return json.toString();
    }







}
