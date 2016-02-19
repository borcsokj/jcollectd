package org.collectd.config.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;

@lombok.Setter
@lombok.Getter
@XmlType(name = "Attribute", namespace = "http://config.collectd.org/types")
@XmlAccessorType(XmlAccessType.FIELD)
public class Attribute {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String alias;
    
    @XmlAttribute
    private String units;
    
    @XmlAttribute
    private String type;
}
