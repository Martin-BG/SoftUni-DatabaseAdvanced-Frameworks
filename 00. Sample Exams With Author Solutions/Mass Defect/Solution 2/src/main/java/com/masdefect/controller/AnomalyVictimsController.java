package com.masdefect.controller;

import com.masdefect.domain.dto.json.AnomalyVictimsJSONDto;
import com.masdefect.parser.interfaces.FileParser;
import com.masdefect.service.AnomalyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class AnomalyVictimsController {
    @Autowired
    private AnomalyService anomalyService;

    @Autowired
    @Qualifier(value = "JSONParser")
    private FileParser jsonParser;

    public String importDataFromJSON(String fileContent){
        AnomalyVictimsJSONDto[] anomalyVictimsDtos = null;
        try {
            anomalyVictimsDtos = this.jsonParser.read(AnomalyVictimsJSONDto[].class, fileContent);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuilder sb = new StringBuilder();
        for (AnomalyVictimsJSONDto anomalyVictimsDto : anomalyVictimsDtos) {
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
}
