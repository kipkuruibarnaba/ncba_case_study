package com.ncbacasestudy.ncba.utils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import com.ncbacasestudy.ncba.dto.CountryInfoDto;

public class CountryInfoParser {
    public static CountryInfoDto parse(String xml) {

        try {
            Document doc = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes()));

            CountryInfoDto dto = new CountryInfoDto();

            dto.setIsoCode(getValue(doc, "sISOCode", 0));
            dto.setName(getValue(doc, "sName", 0));
            dto.setCapitalCity(getValue(doc, "sCapitalCity", 0));
            dto.setPhoneCode(getValue(doc, "sPhoneCode", 0));
            dto.setContinentCode(getValue(doc, "sContinentCode", 0));
            dto.setCurrencyCode(getValue(doc, "sCurrencyISOCode", 0));
            dto.setCountryFlag(getValue(doc, "sCountryFlag", 0));

            // Language section
            dto.setLanguageCode(getValue(doc, "sISOCode", 1));
            dto.setLanguageName(getValue(doc, "sName", 1));

            return dto;

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse SOAP response", e);
        }
    }

    private static String getValue(Document doc, String tagName, int index) {
        NodeList nodes = doc.getElementsByTagNameNS(
                "http://www.oorsprong.org/websamples.countryinfo",
                tagName);

        return nodes.getLength() > index
                ? nodes.item(index).getTextContent()
                : null;
    }
}
