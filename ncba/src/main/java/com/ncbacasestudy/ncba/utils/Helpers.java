package com.ncbacasestudy.ncba.utils;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;

public class Helpers {
    public static String toSentenceCase(String text) {
        if (text == null || text.trim().isEmpty()) {
            return text;
        }

        text = text.trim().toLowerCase();

        return Character.toUpperCase(text.charAt(0))
                + text.substring(1);
    }
    public static String extractIsoCode(String xml) {
        try {
            Document document = DocumentBuilderFactory
                    .newInstance()
                    .newDocumentBuilder()
                    .parse(new ByteArrayInputStream(xml.getBytes()));

            NodeList nodeList =
                    document.getElementsByTagNameNS(
                            "http://www.oorsprong.org/websamples.countryinfo",
                            "CountryISOCodeResult");

            if (nodeList.getLength() > 0) {
                return nodeList.item(0).getTextContent();
            }

        } catch (Exception e) {
            throw new RuntimeException("Failed to parse SOAP response", e);
        }

        return null;
    }
}
