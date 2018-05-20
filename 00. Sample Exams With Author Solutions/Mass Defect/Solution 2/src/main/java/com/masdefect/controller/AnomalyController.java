package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyExportJSONDto;
import com.masdefect.domain.dto.json.AnomalyImportJSONDto;
import com.masdefect.domain.dto.xml.AnomaliesXMLDto;
import com.masdefect.domain.dto.xml.AnomalyXMLDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Controller
public class AnomalyController {
    @Autowired
    private AnomalyService anomalyService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    @Autowired
    @Qualifier(value = "XMLParser")
    private FileParser xmlParser;

    public String importDataFromJSON(String fileContent) {
        AnomalyImportJSONDto[] anomalyVictimsDtos = null;
        try {
            anomalyVictimsDtos = this.jsonParser.read(AnomalyImportJSONDto[].class, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (AnomalyImportJSONDto anomalyVictimsDto : anomalyVictimsDtos) {
            try{
                this.anomalyService.create(anomalyVictimsDto);
                    sb.append(String.format("Successfully imported Anomaly."));
                sb.append(System.lineSeparator());
            } catch (Exception e){
                sb.append(String.format("Error: Invalid data."));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }
    public String importDataFromXML(String fileContent) {
        AnomaliesXMLDto anomaliesImportXMLDtos = null;
        try {
            anomaliesImportXMLDtos = this.xmlParser.read(AnomaliesXMLDto.class, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (AnomalyXMLDto anomalyImportXMLDto : anomaliesImportXMLDtos.getAnomalies()) {
            try{
                this.anomalyService.create(anomalyImportXMLDto);
                sb.append(String.format("Successfully imported Anomaly."));
                sb.append(System.lineSeparator());
            } catch (Exception e){
                sb.append(String.format("Error: Invalid data."));
                sb.append(System.lineSeparator());
            }
        }
        return sb.toString();
    }

    public String findAnomalyWithMostVictims() throws IOException, JAXBException {
        AnomalyExportJSONDto anomalyExportJSONDto = this.anomalyService.findMostAffectingAnomalies();
        String output = this.jsonParser.write(anomalyExportJSONDto);
        return output;
    }

    public String exportAnomaliesOrdered() throws IOException, JAXBException {
        AnomaliesXMLDto anomaliesXMLDto = this.anomalyService.finaAllAnomalies();
        String output = this.xmlParser.write(anomaliesXMLDto);
        return output;
    }
}
