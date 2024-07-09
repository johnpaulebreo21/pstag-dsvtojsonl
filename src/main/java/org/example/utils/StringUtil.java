package org.example.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import static org.example.utils.DateUtil.parseDate;

public class StringUtil {



    public static List<String> splitStrings(String line, String delimeter,ArrayList<String[]> replaceStrings) {
        Pattern pattern = Pattern.compile( delimeter + "(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        replaceStrings.add(new String[]{"^\"|\"$", ""});

        String[] values =  pattern.split(line);
        List<String> finalValues = new ArrayList<>();
        for(String value : values){
            try {
                //parse date
                finalValues.add(parseDate(value));
            } catch (Exception e) {
                if(value.isEmpty()){
                    finalValues.add(value);
                    continue;
                }
                for(String[] arr : replaceStrings){
                    value = value.replaceAll(arr[0],arr[1]);
                    if(value.lastIndexOf(delimeter) == value.length() - 1){
                        value =  value.substring(0, value.length() - 1);
                    }
                }
                finalValues.add(value);
            }

        }

        return finalValues;
    }

    public static boolean isNumeric(String str) {
        // Regular expression to match numeric string
        return str.matches("-?\\d+(\\.\\d+)?");  // Matches integers and decimals (including negatives)
    }

}
