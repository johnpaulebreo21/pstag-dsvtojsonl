package org.example;

import org.example.services.ConverterService;

import java.util.ArrayList;

public class App 
{
    public static void main( String[] args ) throws Exception {
        if(args.length < 3){
            throw new Exception("Please supply sourceFile,targetFile and delimeter");
        }

        String sourceFile = args[0];
        String targetFile = args[1];
        String delimeter = args[2];

        ArrayList<String[]> replaceStrings = new ArrayList<String[]>();
        if(args.length > 3){
            if(args.length % 2 == 0){
                throw new Exception("Complete replacement args");
            }

            for(int x = 3; x < args.length; x++){
                String regex = args[x];
                String replacement = args[x+1];
                replaceStrings.add(new String[]{regex,replacement});
                x++;
            }
        }

        ConverterService service = new ConverterService();
        service.convertToJsonL(sourceFile,targetFile,delimeter,replaceStrings);
        System.out.println("Done conversion on file: " + targetFile);
    }
}
