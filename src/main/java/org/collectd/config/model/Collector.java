package org.collectd.config.model;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@lombok.Setter
@lombok.Getter
@XmlType(name = "Collector", namespace = "http://config.collectd.org/types")
@XmlAccessorType(XmlAccessType.FIELD)
public class Collector {

    @XmlElement(name = "dataset", namespace = "http://config.collectd.org/types")
    private List<CollectorDataset> datasetList;
}
