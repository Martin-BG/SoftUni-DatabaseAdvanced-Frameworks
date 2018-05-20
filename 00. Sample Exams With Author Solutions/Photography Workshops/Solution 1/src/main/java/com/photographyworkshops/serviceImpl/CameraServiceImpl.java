package com.photographyworkshops.serviceImpl;

import com.photographyworkshops.domain.dto.cameras.BasicCameraImportJSONDto;
import com.photographyworkshops.domain.entities.cameras.DSLRCamera;
import com.photographyworkshops.domain.entities.cameras.MirrorlessCamera;
import com.photographyworkshops.parser.interfaces.ModelParser;
import com.photographyworkshops.repository.BasicCameraRepository;
import com.photographyworkshops.service.CameraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CameraServiceImpl implements CameraService {

    @Autowired
    private ModelParser modelParser;

    @Autowired
    private BasicCameraRepository basicCameraRepository;

    @Override
    public void create(BasicCameraImportJSONDto basicCameraDto) {
        switch (basicCameraDto.getType()) {
            case "DSLR":
                DSLRCamera dslrCamera = this.modelParser.convert(basicCameraDto, DSLRCamera.class);
                this.basicCameraRepository.save(dslrCamera);
                break;
            case "Mirrorless":
                MirrorlessCamera mirrorlessCamera = this.modelParser.convert(basicCameraDto, MirrorlessCamera.class);
                this.basicCameraRepository.save(mirrorlessCamera);
                break;
        }
    }
}
