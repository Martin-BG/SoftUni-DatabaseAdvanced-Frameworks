package hiberspring.dtos.views.branch;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "branches")
@XmlAccessorType(XmlAccessType.FIELD)
public class BranchesViewDto {

    @XmlElement(name = "branch")
    private List<BranchViewDto> branchViewDtos;

    public BranchesViewDto() {
    }

    public List<BranchViewDto> getBranchViewDtos() {
        return this.branchViewDtos;
    }

    public void setBranchViewDtos(List<BranchViewDto> branchViewDtos) {
        this.branchViewDtos = branchViewDtos;
    }
}
