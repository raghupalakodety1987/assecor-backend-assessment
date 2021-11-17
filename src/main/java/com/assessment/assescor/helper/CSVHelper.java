package com.assessment.assescor.helper;

import com.assessment.assescor.entity.Person;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVHelper {

    public static String TYPE = "text/csv";
    public static String[] HEADERS = {"Id", "LastName", "FirstName", "Address", "ColorId"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }
        return true;
    }

    public static List<Person> csvToPersons(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withIgnoreHeaderCase().withTrim());) {

            List<Person> persons = new ArrayList<>();
            String color;
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                String lastName = csvRecord.get(0);
                String firstName = csvRecord.get(1);
                String tokens[] = csvRecord.get(2).split(" ");
                String colorId = csvRecord.get(3);

                if (colorId.equals("1"))
                    color = "blau";
                else if (colorId.equals("2"))
                    color = "grün";
                else if (colorId.equals("3"))
                    color = "violett";
                else if (colorId.equals("4"))
                    color = "rot";
                else if (colorId.equals("5"))
                    color = "gelb";
                else if (colorId.equals("6"))
                    color = "türkis";
                else
                    color = "weiß";

                Person person = new Person(
                        Long.MAX_VALUE,
                        /*Long.parseLong(csvRecord.get("Id")),*/
                        /*csvRecord.get("LastName"),*/
                        /*csvRecord.get(0),*/
                        lastName,
                        /*csvRecord.get("FirstName"),*/
                        /*csvRecord.get(1),*/
                        firstName,
                        /*csvRecord.get("Address"),*/
                        /*csvRecord.get(2),*/
                        tokens[0],
                        tokens[1],
                        /*csvRecord.get("ColorId")*/
                        color
                );

                persons.add(person);
            }

            return persons;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }
}
