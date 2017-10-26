package com.paunoski.eantoasinserverjava.xml;

import javax.xml.bind.annotation.XmlElement;
import java.util.List;

public class ItemAttributes {
    @XmlElement(name = "Title")
    public String title;
    @XmlElement(name = "ProductGroup")
    public String productGroup;
    @XmlElement(name = "Feature")
    public List<String> featureList;

}
