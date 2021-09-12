package ru.job4j.io.csvread;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import ru.job4j.io.ArgsName;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class CSVReaderTest {
    private File file;
    private File target;
    private String data;

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Before
    public void initFile() throws IOException {
         data = String.join(
                System.lineSeparator(),
                "name;age;last_name;education",
                "Tom;20;Smith;Bachelor",
                "Jack;25;Johnson;Undergraduate",
                "William;30;Brown;Secondary special"
        );
         file = temporaryFolder.newFile("source.csv");
         target = temporaryFolder.newFile("target.csv");
    }

    @Test
    public void whenFilterOneColumn() throws Exception {
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "age",
                "20",
                "25",
                "30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenFilterTwoColumns() throws Exception {
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=name,age"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "name;age",
                "Tom;20",
                "Jack;25",
                "William;30"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenFilterLastColumn() throws Exception {
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=education"
        });
        Files.writeString(file.toPath(), data);
        String expected = String.join(
                System.lineSeparator(),
                "education",
                "Bachelor",
                "Undergraduate",
                "Secondary special"
        ).concat(System.lineSeparator());
        CSVReader.handle(argsName);
        Assert.assertEquals(expected, Files.readString(target.toPath()));
    }

    @Test
    public void whenWrongFilterThenEmptyFile() throws Exception {
        ArgsName argsName = ArgsName.of(new String[]{
                "-path=" + file.getAbsolutePath(), "-delimiter=;", "-out=" + target.getAbsolutePath(), "-filter=cars"
        });
        Files.writeString(file.toPath(), data);
        CSVReader.handle(argsName);
        Assert.assertEquals("", Files.readString(target.toPath()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenWrongArgs() {
        ArgsName argsName = ArgsName.of(new String[]{"Wrong Args=Error"});
        CSVReader.handle(argsName);
    }
}