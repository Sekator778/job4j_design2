package ru.job4j.serialization.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.serialization.Contact;
import ru.job4j.serialization.json.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class.getName());

    public static void main(String[] args) throws JAXBException {
        Person person = new Person(true, 43, new Contact("777"), "Worker", "Student", "Married");
        /*Получаем контекст для доступа к АПИ*/
        JAXBContext context = JAXBContext.newInstance(Person.class);
        /*Создаем сериализатор*/
        Marshaller marshaller = context.createMarshaller();
        /*Указываем как будем форматировать*/
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xmlPerson = "";
        try (StringWriter writer = new StringWriter();
                FileWriter fileWriter = new FileWriter("C:\\Temp\\person.xml")) {
            /*Сериализуем*/
            marshaller.marshal(person, writer);
            xmlPerson = writer.getBuffer().toString();
            fileWriter.write(xmlPerson);
            System.out.println(xmlPerson);
        } catch (Exception e) {
            LOG.error("Exception ", e);
        }
        /*Для десериализации нам нужно создать десериализатор*/
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xmlPerson)) {
            /*десериализуем*/
            Person personFromXml = (Person) unmarshaller.unmarshal(reader);
            System.out.println(personFromXml);
        }
    }
}
