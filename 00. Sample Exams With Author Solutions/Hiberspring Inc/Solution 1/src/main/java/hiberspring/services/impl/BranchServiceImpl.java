package hiberspring.services.impl;

import hiberspring.dtos.imports.BranchImportFromJsonDto;
import hiberspring.dtos.views.branch.BranchViewDto;
import hiberspring.dtos.views.branch.BranchesViewDto;
import hiberspring.entities.Branch;
import hiberspring.entities.Town;
import hiberspring.repositories.BranchRepository;
import hiberspring.repositories.TownRepository;
import hiberspring.services.BranchService;
import hiberspring.utilities.MapperConverter;
import hiberspring.validators.EntityValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final TownRepository townRepository;
    private final MapperConverter mapperConverter;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository, MapperConverter mapperConverter) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
        this.mapperConverter = mapperConverter;
    }

    @Override
    public Branch createOne(BranchImportFromJsonDto branchImportFromJsonDto) {
        Branch branch = this.mapperConverter.convert(branchImportFromJsonDto, Branch.class);
        Town town = null;
        if (branch.getTown() != null) {
            town = this.townRepository.getTownByName(branch.getTown().getName());
        }
        branch.setTown(town);
        if (EntityValidator.isValid(branch)) {
            branch = this.branchRepository.save(branch);
            return branch;
        }
        return null;
    }

    @Override
    public BranchesViewDto getTopBranches() {
        List<Object> topBranches = this.branchRepository.getTopBranches();
        List<BranchViewDto> branchViewDtos = new ArrayList<>();
        for (Object branch : topBranches) {
            Object[] objArray = (Object[]) branch;
            BranchViewDto branchViewDto = new BranchViewDto();
            branchViewDto.setName((String) objArray[0]);
            branchViewDto.setTownName((String) objArray[1]);
            branchViewDto.setCount((Long) objArray[2]);
            branchViewDtos.add(branchViewDto);
        }
        BranchesViewDto branchesViewDto = new BranchesViewDto();
        branchesViewDto.setBranchViewDtos(branchViewDtos);
        return branchesViewDto;
    }
}
