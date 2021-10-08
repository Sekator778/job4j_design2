package ru.job4j.serialization.xml;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.serialization.json.subject.Asic;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;

public class SendXml {
    private static final Logger LOG = LoggerFactory.getLogger(SendXml.class.getName());

    public static void main(String[] args) throws JAXBException {
        final Asic asic = new Asic("RusMiner_v2.1");
        JAXBContext context = JAXBContext.newInstance(Asic.class);
        Marshaller marshaller = context.createMarshaller();
        Unmarshaller unmarshaller = context.createUnmarshaller();
        String nameFileOutput = "C:" + File.separator + "Temp" + File.separator + "asic.xml";
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(asic, writer);
            String asicToXml = writer.getBuffer().toString();
            System.out.println(asicToXml);
            try (StringReader reader = new StringReader(asicToXml);
                 FileWriter fileWriter = new FileWriter(nameFileOutput)) {
                Asic asicFromPerson = (Asic) unmarshaller.unmarshal(reader);
                fileWriter.write(asicToXml);
                System.out.println("=======xml====to======person=======");
                System.out.println(asicFromPerson);
            }
        } catch (Exception e) {
            LOG.error("Exception ---->>>>", e);
        }
    }
}
