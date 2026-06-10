package com.ncbacasestudy.ncba.service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.xml.sax.SAXException;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CountryService {
    private final RestTemplate restTemplate;

    public CountryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getCountryInfo(String countryName) throws XPathExpressionException, ParserConfigurationException, IOException, SAXException {
        String res =null;
      try{
        // First SOAP call: Get ISO cod
          String soapEndpointUrl ="http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";
        System.out.println("HEllo "+countryName);
          System.out.println("HEllo "+countryName);
        String reqBody ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.oorsprong.org/websamples.countryinfo\">\n" +
                "   <soapenv:Header/>\n" +
                "   <soapenv:Body>\n" +
                "      <web:CountryISOCode>\n" +
                "         <web:sCountryName>"+countryName+"</web:sCountryName>\n" +
                "      </web:CountryISOCode>\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>";
          // Open connection
          URL url = new URL(soapEndpointUrl);
          HttpURLConnection connection = (HttpURLConnection) url.openConnection();

          // Set headers
          connection.setRequestMethod("POST");
          connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
          connection.setRequestProperty("Content-Length", String.valueOf(reqBody.length()));
//          connection.setRequestProperty("Authorization", "Bearer " + token);
          connection.setDoOutput(true);

          // Send SOAP request
          OutputStream os = connection.getOutputStream();
          os.write(reqBody.getBytes());
          os.flush();
          os.close();
          // Read response
          int responseCode = connection.getResponseCode();

          BufferedReader in = new BufferedReader(new InputStreamReader(
                  connection.getInputStream()));
          String inputLine;
          StringBuilder response = new StringBuilder();

          while ((inputLine = in.readLine()) != null) {
              response.append(inputLine);
          }
          in.close();
          System.out.println("RESPONSE "+response.toString());
          res=response.toString();
      } catch (Exception e) {
          e.printStackTrace();
      }
//        return res;
        String xml = res;

        Pattern pattern = Pattern.compile(
                "<m:CountryISOCodeResult>(.*?)</m:CountryISOCodeResult>");

        Matcher matcher = pattern.matcher(xml);

        String isoCode = matcher.find() ? matcher.group(1) : null;
        return  getFullCountryInfo(isoCode);
    }
    public String getFullCountryInfo(String countryISO) throws ParserConfigurationException, IOException, SAXException, XPathExpressionException {
        String res =null;
        try{
            // First SOAP call: Get ISO cod
            String soapEndpointUrl ="http://webservices.oorsprong.org/websamples.countryinfo/CountryInfoService.wso";
            String reqBody ="<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:web=\"http://www.oorsprong.org/websamples.countryinfo\">\n" +
                    "   <soapenv:Header/>\n" +
                    "   <soapenv:Body>\n" +
                    "      <web:FullCountryInfo>\n" +
                    "         <web:sCountryISOCode>"+countryISO+"</web:sCountryISOCode>\n" +
                    "      </web:FullCountryInfo>\n" +
                    "   </soapenv:Body>\n" +
                    "</soapenv:Envelope>";
            // Open connection
            URL url = new URL(soapEndpointUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // Set headers
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            connection.setRequestProperty("Content-Length", String.valueOf(reqBody.length()));
//          connection.setRequestProperty("Authorization", "Bearer " + token);
            connection.setDoOutput(true);

            // Send SOAP request
            OutputStream os = connection.getOutputStream();
            os.write(reqBody.getBytes());
            os.flush();
            os.close();
            // Read response
            int responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            res=response.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        return res;
        String xml = res;
        Document document = DocumentBuilderFactory.newInstance()
                .newDocumentBuilder()
                .parse(new InputSource(new StringReader(xml)));

        XPath xpath = XPathFactory.newInstance().newXPath();

        String isoCode = xpath.evaluate(
                "//*[local-name()='FullCountryInfoResult']/*[local-name()='sISOCode'][1]",
                document);

        String countryName = xpath.evaluate(
                "//*[local-name()='FullCountryInfoResult']/*[local-name()='sName'][1]",
                document);

        String capitalCity = xpath.evaluate(
                "//*[local-name()='sCapitalCity']",
                document);

        String phoneCode = xpath.evaluate(
                "//*[local-name()='sPhoneCode']",
                document);

        String continentCode = xpath.evaluate(
                "//*[local-name()='sContinentCode']",
                document);

        String currencyCode = xpath.evaluate(
                "//*[local-name()='sCurrencyISOCode']",
                document);

        String flag = xpath.evaluate(
                "//*[local-name()='sCountryFlag']",
                document);

        String languageCode = xpath.evaluate(
                "//*[local-name()='tLanguage']/*[local-name()='sISOCode']",
                document);

        String languageName = xpath.evaluate(
                "//*[local-name()='tLanguage']/*[local-name()='sName']",
                document);

        System.out.println(isoCode);
        System.out.println(countryName);
        System.out.println(capitalCity);
        System.out.println(phoneCode);
        System.out.println(continentCode);
        System.out.println(currencyCode);
        System.out.println(flag);
        System.out.println(languageCode);
        System.out.println(languageName);
        return xml;
    }



}
