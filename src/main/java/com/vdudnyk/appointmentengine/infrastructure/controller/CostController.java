package com.vdudnyk.appointmentengine.infrastructure.controller;

import com.vdudnyk.appointmentengine.application.accounting.costs.CostsFacade;
import com.vdudnyk.appointmentengine.application.accounting.costs.shared.AddCostRequest;
import com.vdudnyk.appointmentengine.application.accounting.costs.shared.CostDTO;
import com.vdudnyk.appointmentengine.application.accounting.costs.shared.UpdateCostRequest;
import com.vdudnyk.appointmentengine.application.shared.StatusResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("costs")
@AllArgsConstructor
public class CostController {
    private final CostsFacade costsFacade;

    @PostMapping
    public ResponseEntity<StatusResponse> addCost(@RequestBody AddCostRequest addCostRequest) {
        return ResponseEntity.ok(costsFacade.addCost(addCostRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StatusResponse> deleteCost(@PathVariable Long id) {
        return ResponseEntity.ok(costsFacade.deleteCost(id));
    }

    @GetMapping
    public ResponseEntity<List<CostDTO>> getCosts() {
        return ResponseEntity.ok(costsFacade.getCosts());
    }

    @PutMapping
    public ResponseEntity<StatusResponse> updateCost(@RequestBody UpdateCostRequest updateCostRequest) {
        return ResponseEntity.ok(costsFacade.updateCost(updateCostRequest));
    }
}
