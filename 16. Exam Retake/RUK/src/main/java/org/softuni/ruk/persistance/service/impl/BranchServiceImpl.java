package org.softuni.ruk.persistance.service.impl;

import org.softuni.ruk.config.Config;
import org.softuni.ruk.model.dto.binding.json.BranchFromJsonDto;
import org.softuni.ruk.model.entity.Branch;
import org.softuni.ruk.parser.ValidationUtil;
import org.softuni.ruk.parser.interfaces.ModelParser;
import org.softuni.ruk.persistance.repository.BranchRepository;
import org.softuni.ruk.persistance.service.api.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class BranchServiceImpl implements BranchService {

    private final BranchRepository repository;
    private final ModelParser mapper;
    private final ValidationUtil validator;


    @Autowired
    public BranchServiceImpl(final BranchRepository repository,
                             final ModelParser mapper,
                             final ValidationUtil validator) {
        this.repository = repository;
        this.mapper = mapper;
        this.validator = validator;
    }

    @Override
    public <T> String create(final T t) {
        BranchFromJsonDto dto = (BranchFromJsonDto) t;

        if (!this.validator.isValid(dto) ||
                this.repository.findOneByName(dto.getName()) != null) {
            return Config.ERROR_INCORRECT_DATA;
        }

        Branch branch = this.mapper.convert(dto, Branch.class);

        this.repository.saveAndFlush(branch);

        return String.format(Config.SUCCESSFULLY_IMPORTED, "Branch", branch.getName());
    }

    Branch findOneByName(final String name) {
        return this.repository.findOneByName(name);
    }
}
