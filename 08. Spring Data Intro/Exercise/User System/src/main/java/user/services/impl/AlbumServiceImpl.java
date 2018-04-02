package user.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import user.repositories.AlbumRepository;
import user.services.api.AlbumService;

import javax.transaction.Transactional;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    @Autowired
    public AlbumServiceImpl(final AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

}
