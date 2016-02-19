package org.collectd.config.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@lombok.Setter
@lombok.Getter
@XmlRootElement(name = "jcollectd-config", namespace = "http://config.collectd.org/types")
@XmlType(name = "JCollectdConfig", namespace = "http://config.collectd.org/types")
@XmlAccessorType(XmlAccessType.FIELD)
public class Configuration {

    @XmlElement(name = "plugin")
    private List<Plugin> pluginList;
    
    public List<Plugin> getPluginList() {
        if (pluginList == null) {
            pluginList = new ArrayList<>();
        }
        return pluginList;
    }
}
