package user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.repositories.TownRepository;
import user.services.api.TownService;

import javax.transaction.Transactional;

@Service
@Transactional
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(final TownRepository townRepository) {
        this.townRepository = townRepository;
    }

}
