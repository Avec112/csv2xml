package no.avec.object;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by avec on 08/12/2016.
 */
@Data
@XmlRootElement(name = "Person")
@XmlType(propOrder={"id","name", "address", "additionalInfo"}) // used for xml
@JsonPropertyOrder({"id","name", "address", "additionalInfo"}) // used by jackson-dataformat-csv
public class Person {
    private String id;
    private String name;
    private String address;
    private String additionalInfo;

}
