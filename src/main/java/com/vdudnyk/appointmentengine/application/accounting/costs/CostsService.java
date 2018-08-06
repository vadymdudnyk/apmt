package com.vdudnyk.appointmentengine.application.accounting.costs;

import com.vdudnyk.appointmentengine.application.accounting.costs.shared.AddCostRequest;
import com.vdudnyk.appointmentengine.application.accounting.costs.shared.CategoryDTO;
import com.vdudnyk.appointmentengine.application.accounting.costs.shared.CostDTO;
import com.vdudnyk.appointmentengine.application.accounting.costs.shared.UpdateCostRequest;
import com.vdudnyk.appointmentengine.application.salon.SalonFacade;
import com.vdudnyk.appointmentengine.application.shared.Status;
import com.vdudnyk.appointmentengine.application.shared.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
class CostsService {

    private final CostRepository costRepository;
    private final CategoryRepository categoryRepository;
    private final SalonFacade salonFacade;

    StatusResponse addCost(AddCostRequest addCostRequest) {
        Set<Category> categories = createOrGetCategories(addCostRequest.getCategories());
        Cost cost = new Cost();
        cost.setName(addCostRequest.getName());
        cost.setPrice(addCostRequest.getPrice());
        cost.setCategories(categories);
        cost.setDate(addCostRequest.getDate());
        cost.setSalonId(salonFacade.getUserSalon().getId());
        costRepository.save(cost);
        return new StatusResponse(Status.SUCCESS);
    }

    StatusResponse deleteCost(Long id) {
        costRepository.findByIdAndSalonId(id, salonFacade.getUserSalon().getId())
                      .ifPresent(cost -> costRepository.deleteById(cost.getId()));
        return new StatusResponse(Status.SUCCESS);
    }

    List<CostDTO> getCosts() {
        return costRepository.findAllBySalonId(salonFacade.getUserSalon().getId())
                             .stream()
                             .map(this::mapCostToCostDTO)
                             .collect(Collectors.toList());
    }

    StatusResponse updateCost(UpdateCostRequest updateCostRequest) {
        Optional<Cost> optionalCost = costRepository.findById(updateCostRequest.getId());
        optionalCost.ifPresent(cost -> {
            cost.setName(updateCostRequest.getName());
            cost.setPrice(updateCostRequest.getPrice());
            cost.setDate(updateCostRequest.getDate());
            cost.setCategories(createOrGetCategories(updateCostRequest.getCategories()));
            costRepository.save(cost);
        });
        return new StatusResponse(Status.SUCCESS);
    }

    private Set<Category> createOrGetCategories(List<String> categories) {
        return categories
                .stream()
                .map(categoryName ->
                             categoryRepository.findByName(categoryName)
                                               .orElseGet(() -> categoryRepository.save(new Category()
                                                                                                .withName(categoryName))))
                .collect(Collectors.toSet());
    }

    private CostDTO mapCostToCostDTO(Cost cost) {
        CostDTO costDTO = new CostDTO();
        costDTO.setCategories(mapCategoriesToCategoriesDTO(cost.getCategories()));
        costDTO.setCurrency(cost.getCurrency());
        costDTO.setDate(cost.getDate());
        costDTO.setId(cost.getId());
        costDTO.setPrice(cost.getPrice());
        costDTO.setName(cost.getName());
        return costDTO;
    }

    private List<CategoryDTO> mapCategoriesToCategoriesDTO(Set<Category> categories) {
        return categories
                .stream()
                .map(category -> new CategoryDTO(category.getName(), category.getId()))
                .collect(Collectors.toList());
    }
}
