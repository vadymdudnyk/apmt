package com.vdudnyk.appointmentengine.application.accounting.costs;

import com.vdudnyk.appointmentengine.application.accounting.costs.shared.AddCostRequest;
import com.vdudnyk.appointmentengine.application.accounting.costs.shared.CostDTO;
import com.vdudnyk.appointmentengine.application.accounting.costs.shared.UpdateCostRequest;
import com.vdudnyk.appointmentengine.application.shared.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CostsFacade {
    private final CostsService costsService;

    public StatusResponse addCost(AddCostRequest addCostRequest) {
        return costsService.addCost(addCostRequest);
    }

    public StatusResponse deleteCost(Long id) {
        return costsService.deleteCost(id);
    }

    public List<CostDTO> getCosts() {
        return costsService.getCosts();
    }

    public StatusResponse updateCost(UpdateCostRequest updateCostRequest) {
        return costsService.updateCost(updateCostRequest);
    }
}
