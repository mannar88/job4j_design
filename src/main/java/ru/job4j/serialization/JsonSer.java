package ru.job4j.serialization;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.List;

@XmlRootElement(name = "jsonSer")
@XmlAccessorType(XmlAccessType.FIELD)
public class JsonSer {
    @XmlAttribute
    private boolean bool;
    @XmlAttribute
    private int anInt;
    private String string;
    private char[] chars;
    private List<Double> list;

    public JsonSer() {

    }

    public JsonSer(boolean bool, int anInt, String string, char[] chars, List<Double> list) {
        this.bool = bool;
        this.anInt = anInt;
        this.string = string;
        this.chars = chars;
        this.list = list;
    }

    public static void main(String[] args) throws JAXBException, IOException {
        JsonSer jsonSer = new JsonSer(true, 1, "test", new char[]{'a', 'b', 'c'}, List.of(5.0));
        JAXBContext context = JAXBContext.newInstance(JsonSer.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(jsonSer, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            JsonSer result = (JsonSer) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }

    }

    @Override
    public String toString() {
        return "JsonSer{"
                + "bool=" + bool
                + ", anInt=" + anInt
                + ", string='" + string + '\''
                + ", chars=" + Arrays.toString(chars)
                + ", list=" + list
                + '}';
    }
}
