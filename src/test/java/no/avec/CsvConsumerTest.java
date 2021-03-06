package no.avec;

import no.avec.csv.CsvConsumer;
import no.avec.object.Person;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by avec on 08/12/2016.
 */
public class CsvConsumerTest {
    @Test
    public void getValuesByStrTokenizer() throws Exception {

        File file = new File(this.getClass().getResource("/persons.csv").getFile());
        List<Person> values = CsvConsumer.getValues(file); // StrTokenizer (apache commons-lang3)
        assertEquals(values.size(), 2);
        assertEquals(values.get(0).getId(), "1");
        assertEquals(values.get(1).getId(), "2");

    }

    @Test
    public void getValuesByJacksonDataformatCsv() throws Exception {

        File file = new File(this.getClass().getResource("/persons.csv").getFile());
        List<Person> values = CsvConsumer.getValues2(file); // jackson-dataformat-csv
        assertEquals(values.size(), 2);
        assertEquals(values.get(0).getId(), "1");
        assertEquals(values.get(1).getId(), "2");

    }

}