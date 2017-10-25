package com.paunoski.eantoasinserverjava.xml;

import javax.xml.bind.annotation.XmlElement;

public class ItemAttributes {
    @XmlElement(name = "Title")
    String title;
    @XmlElement(name = "ProductGroup")
    String productGroup;

}
