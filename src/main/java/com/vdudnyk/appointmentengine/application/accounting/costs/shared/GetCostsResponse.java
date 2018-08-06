package com.vdudnyk.appointmentengine.application.accounting.costs.shared;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GetCostsResponse {
    private List<CostDTO> costs = new ArrayList<>();
}
