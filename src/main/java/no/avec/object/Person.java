package no.avec.object;

import lombok.Data;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Created by avec on 08/12/2016.
 */
@Data
@XmlRootElement(name = "Person")
@XmlType(propOrder={"id","name", "address", "additionalInfo"})
public class Person {
    private String id;
    private String name;
    private String address;
    private String additionalInfo;

}
