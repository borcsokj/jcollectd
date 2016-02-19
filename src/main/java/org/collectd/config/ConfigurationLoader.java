package org.collectd.config;

import java.io.File;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import org.collectd.config.model.Configuration;

public class ConfigurationLoader {

    private JAXBContext jaxbContext;
    private Unmarshaller jaxbUnmarshaller;

    public ConfigurationLoader() {
        try {
            jaxbContext = JAXBContext.newInstance(Configuration.class);
            jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        } catch (JAXBException ex) {
        }
    }

    public static void main(String[] args) throws JAXBException {
        final Configuration conf = new ConfigurationLoader().load("choice");
        System.out.println("conf = " + conf);
    }

    public Configuration load(final String name) throws JAXBException {
        return (Configuration) jaxbUnmarshaller.unmarshal(new File("etc/" + name + "-jcollectd.xml"));
    }
}
