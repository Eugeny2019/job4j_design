package ru.job4j.serialization.json;

import ru.job4j.io.serialization.xml.Contact;
import ru.job4j.io.serialization.xml.Person;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;

public class MainSerializetoXml {

    public static void main(String[] args) throws Exception {
        ru.job4j.io.serialization.xml.Person person = new ru.job4j.io.serialization.xml.Person(false, 30, new Contact("11-111"), "Worker", "Married");
        /* Получаем контекст для доступа к АПИ */
        JAXBContext context = JAXBContext.newInstance(ru.job4j.io.serialization.xml.Person.class);
        /* Создаем сериализатор */
        Marshaller marshaller = context.createMarshaller();
        /* Указываем, что нам нужно форматирование */
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            /* Сериализуем */
            marshaller.marshal(person, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            /* десериализуем */
            ru.job4j.io.serialization.xml.Person result = (Person) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
