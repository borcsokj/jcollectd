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
@XmlType(name = "Plugin", namespace = "http://config.collectd.org/types")
@XmlAccessorType(XmlAccessType.FIELD)
public class Plugin {

    @XmlAttribute
    private String name;

    @XmlElement(name = "mbean")
    private List<MBean> mbeanList;
    
    @XmlElement(name = "collector")
    private List<Collector> collectorList;

    public List<MBean> getMbeanList() {
        if (mbeanList == null) {
            mbeanList = new ArrayList<>();
        }
        return mbeanList;
    }
    
    public List<Collector> getCollectorList() {
        if (collectorList == null) {
            collectorList = new ArrayList<>();
        }
        return collectorList;
    }
}
