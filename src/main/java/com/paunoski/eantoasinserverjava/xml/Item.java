package com.paunoski.eantoasinserverjava.xml;

import javax.xml.bind.annotation.XmlElement;

public class Item {
    @XmlElement(name = "ASIN")
    public String asin;

    @XmlElement(name = "ItemAttributes")
    public ItemAttributes itemAttributes;
}
