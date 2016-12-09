package no.avec.csv;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.extern.slf4j.Slf4j;
import no.avec.object.Person;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.text.StrTokenizer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by avec on 08/12/2016.
 */
public class CsvConsumer {

    /*
        With use of StrTokenizer (commons-lang3)
     */
    public static List<Person> getValues(File file) throws IOException {
        List<Person> objs = new ArrayList<>();
        List<String> lines = FileUtils.readLines(file, "UTF-8");
        for(String line:lines) {
            StrTokenizer tokenizer = new StrTokenizer(line, ':', '"');
            List<String> values = tokenizer.getTokenList();

            Person obj = new Person();
            obj.setId(values.get(0));
            obj.setName(values.get(1));
            obj.setAddress(values.get(2));
            obj.setAdditionalInfo(values.get(3));

            objs.add(obj);
        }
        return objs;
    }

    /*
        With use of jackson-dataformat-csv
     */
    public static List<Person> getValues2(File file) throws IOException {
        CsvMapper mapper = new CsvMapper();
        CsvSchema schema = mapper.schemaFor(Person.class).withColumnSeparator(':');
        MappingIterator<Person> persons = mapper.readerFor(Person.class).with(schema).readValues(file);
//        System.out.println(persons);
        return persons.readAll();
    }

}
