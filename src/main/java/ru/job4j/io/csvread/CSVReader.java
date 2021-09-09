package ru.job4j.io.csvread;

import java.io.FileNotFoundException;

/**
 * java -jar target/CSVReader.jar -path=./data/forScanner.csv -delimiter=","  -out=console -filter=name,age
 */
public class CSVReader {
    public static void main(String[] args) throws FileNotFoundException {
        Reader reader = new Reader();
        reader.exec(args);
    }
}
