package app.exam.service.impl;

import app.exam.domain.dto.xml.CategoriesFrequentItemsXMLExportDTO;
import app.exam.domain.dto.xml.CategoryExportDTO;
import app.exam.domain.dto.xml.MostPopularItemDTO;
import app.exam.domain.entities.OrderItem;
import app.exam.repository.CategoryRepository;
import app.exam.repository.ItemsRepository;
import app.exam.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private ItemsRepository itemsRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public CategoriesFrequentItemsXMLExportDTO getCategoriesWithMostPopularItems(List<String> categoryNames) {
        List<CategoryExportDTO> categoriesExported = this.categoryRepository.findByNameIn(categoryNames)
                .stream()
                .map(c -> new CategoryExportDTO(c.getName(),
                        c.getItems().stream()
                                .map(i -> new MostPopularItemDTO(
                                        i.getName(),
                                        i.getOrderItems().stream().mapToDouble(oi -> oi.getQuantity() * oi.getItem().getPrice().doubleValue()).sum(),
                                        i.getOrderItems().stream().mapToInt(OrderItem::getQuantity).sum()))
                                .sorted((mpi1, mpi2) -> {
                                    int cmp = mpi2.getTotalMade().compareTo(mpi1.getTotalMade());
                                    return cmp != 0 ? cmp : mpi2.getTimesSold().compareTo(mpi1.getTimesSold());
                                })
                                .findFirst()
                                .get()
                ))
                .sorted((ce1, ce2) -> {
                    int cnt = ce2.getMostPopularItemDTO().getTotalMade().compareTo(ce1.getMostPopularItemDTO().getTotalMade());
                    return cnt != 0 ? cnt : ce2.getMostPopularItemDTO().getTimesSold().compareTo(ce1.getMostPopularItemDTO().getTimesSold());
                })
                .collect(Collectors.toList());
        CategoriesFrequentItemsXMLExportDTO export = new CategoriesFrequentItemsXMLExportDTO();
        export.setCategoriesWithMostFrequentItem(categoriesExported);
        return export;
    }
}
