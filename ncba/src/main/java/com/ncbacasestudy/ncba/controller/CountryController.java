package com.ncbacasestudy.ncba.controller;

import com.ncbacasestudy.ncba.dto.CountryReqDto;
import com.ncbacasestudy.ncba.model.CountryInfo;
import com.ncbacasestudy.ncba.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.List;
//import static  com.ncbacasestudy.ncba.utils.Helpers.toSentenceCase;

@RestController
@RequestMapping("api/country")
public class CountryController {
    private CountryService countryService;
    public CountryController(CountryService countryServiceImpl) {
        this.countryService = countryServiceImpl;
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
    // Fetch all country information
    @GetMapping
    public ResponseEntity<List<CountryInfo>> getAllCountries() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }
    // Fetch country information by ID
    @GetMapping("/{id}")
    public ResponseEntity<CountryInfo> getCountryById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                countryService.getCountryById(id));
    }

    // Update country information
    @PutMapping("/{id}")
    public ResponseEntity<CountryInfo> updateCountry(
            @PathVariable Long id,
            @RequestBody CountryInfo country) {

        return ResponseEntity.ok(
                countryService.updateCountry(id, country));
    }

    // Delete country information
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCountry(
            @PathVariable Long id) {

        countryService.deleteCountry(id);

        return ResponseEntity.ok("Country deleted successfully");
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
