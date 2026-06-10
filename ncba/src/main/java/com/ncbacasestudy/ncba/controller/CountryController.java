package com.ncbacasestudy.ncba.controller;

import com.ncbacasestudy.ncba.dto.CountryReqDto;
import com.ncbacasestudy.ncba.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
//import static  com.ncbacasestudy.ncba.utils.Helpers.toSentenceCase;

@RestController
@RequestMapping("api/country")
public class CountryController {
    public CountryService countryService;
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }
    @PostMapping
    public ResponseEntity<String> receiveCountry(@RequestBody CountryReqDto req) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {

        System.out.println("request "+req);
        String countryName = req.getName();
        System.out.println("countryName "+countryName);
        String sentenceCaseName = toSentenceCase(countryName);;

        String countryInfo =
                countryService.getCountryInfo(sentenceCaseName);

        return ResponseEntity.ok(countryInfo);
    }

    private String toSentenceCase(String text) {
        if (text == null || text.trim().isEmpty()) {
            return text;
        }

        text = text.trim().toLowerCase();

        return Character.toUpperCase(text.charAt(0))
                + text.substring(1);
    }
}
