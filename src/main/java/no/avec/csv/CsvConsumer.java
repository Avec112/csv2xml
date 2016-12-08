package no.avec.csv;

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

    public static List<Person> getValues(File file) throws IOException {
        List<Person> objs = new ArrayList<Person>();
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
}
