package no.avec;

import no.avec.object.Person;
import no.avec.xml.XmlProducer;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static junit.framework.TestCase.assertTrue;

/**
 * Created by avec on 08/12/2016.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class XmlProducerTest {

    private static final String XML_FILE = "person.xml"; // workdir
    private static final String SCHEMA_FILE = "persons.xsd"; // testresource
    private File xml;

    @Before
    public void setUp() throws Exception {
        try {
            xml = new File(XML_FILE);
            if (xml.exists()) {
                FileUtils.forceDelete(xml);
            }
        } catch(Exception e) {
            // do nothing
        }

    }

    @After
    public void tearDown() throws Exception {
//        xml.delete();
    }

    @Test // run before validation
    public void marshal() throws Exception {
        List<Person> objs = Arrays.asList(getPerson("1"), getPerson("2"));
        XmlProducer.marshal(objs, "Persons", new File(getTestResourcePath() + XML_FILE));
        xmlValidation();

    }

    @Test
    public void exampleValidation() {
        boolean valid = validate("example.xml", "example.xsd");
        assertTrue("example.xml is not valid", valid);
    }

    private void xmlValidation() {

        boolean valid = validate(XML_FILE, SCHEMA_FILE);
        System.out.printf("%s validation = %b.", XML_FILE, valid);
        assertTrue("xml is not valid", valid);
    }


    private boolean validate(String xmlFile, String schemaFile) {
        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            Schema schema = schemaFactory.newSchema(new File(getResource(schemaFile)));

            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new File(getResource(xmlFile))));
            return true;
        } catch (SAXException | IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private String getResource(String filename) throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(filename);
        Objects.requireNonNull(resource);

        return resource.getFile();
    }

    private String getTestResourcePath() throws FileNotFoundException {
        URL resource = getClass().getClassLoader().getResource(".");

        return resource.getPath();
    }



    private Person getPerson(String id) {
        Person obj = new Person();
        obj.setId(id);
        obj.setName("name" + id);
        obj.setAddress("address" + id);
        obj.setAdditionalInfo("additional info " + id);
        return obj;
    }
}