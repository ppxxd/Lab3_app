package ru.andreykrutskikh.lab3_app;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Attribute;

import java.util.List;

/*
@Root(name = "ValCurs", strict = false)
public class CurrencyResponse {
    @ElementList(inline = true, required = false)
    private List<Valute> valutes;

    public List<Valute> getValutes() {
        return valutes;
    }

    @Root(name = "Valute", strict = false)
    public static class Valute {
        @Attribute(name = "ID", required = false)
        private String id;

        @Element(name = "NumCode", required = false)
        private String numCode;

        @Element(name = "CharCode", required = false)
        private String charCode;

        @Element(name = "Nominal", required = false)
        private String nominal;

        @Element(name = "Name", required = false)
        private String name;

        @Element(name = "Value", required = false)
        private String value;

        public String getCharCode() {
            return charCode;
        }

        public String getName() {
            return name;
        }

        public String getValue() {
            return value;
        }
    }
}*/
