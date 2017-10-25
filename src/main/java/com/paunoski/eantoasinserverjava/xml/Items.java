package com.paunoski.eantoasinserverjava.xml;

import javax.xml.bind.annotation.XmlElement;

public class Items {
    @XmlElement(name = "Item")
    public Item item;
}
