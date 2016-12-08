package no.avec.xml;

import no.avec.object.Person;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.File;
import java.util.List;

/**
 * Created by avec on 08/12/2016.
 */
public class XmlProducer {

    public static void marshal(List<?> list, String name, File xml)
            throws JAXBException {
        JAXBContext jc = JAXBContext.newInstance(Wrapper.class, Person.class);
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        QName qName = new QName(name);
        Wrapper wrapper = new Wrapper(list);
        JAXBElement<Wrapper> jaxbElement = new JAXBElement<>(qName,
                Wrapper.class, wrapper);
        marshaller.marshal(jaxbElement, xml);
    }

}
