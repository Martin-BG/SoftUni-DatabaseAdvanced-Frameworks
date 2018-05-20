package app.service.impl;

import app.model.entities.BasicCamera;
import app.model.validation.ValidationUtil;
import app.repositories.CamerasRepository;
import app.service.api.CamerasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class CamerasServiceImpl implements CamerasService {

    @Autowired
    private CamerasRepository camerasDao;

    @Override
    public BasicCamera add(BasicCamera camera) {
        BasicCamera persistedCamera = null;
        if (ValidationUtil.isValid(camera)) {
            persistedCamera = camerasDao.save(camera);
            System.out.println("Successfully imported " + camera);
        } else {
            System.out.println("Error. Invalid data provided");
        }
        return persistedCamera;
    }
}
