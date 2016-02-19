package org.collectd.config.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@lombok.Setter
@lombok.Getter
@XmlType(name = "MBean", namespace = "http://config.collectd.org/types")
@XmlAccessorType(XmlAccessType.FIELD)
public class MBean {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private String alias;

    @XmlElement(name = "attribute")
    private List<Attribute> attributeList;

    public List<Attribute> getAttributeList() {
        if (attributeList == null) {
            attributeList = new ArrayList<>();
        }
        return attributeList;
    }
}
