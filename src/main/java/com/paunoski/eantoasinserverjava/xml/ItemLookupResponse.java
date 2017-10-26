package com.paunoski.eantoasinserverjava.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "ItemLookupResponse")
public class ItemLookupResponse {

    @XmlElement(name = "Items")
    public Items items;
}
