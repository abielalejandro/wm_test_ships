package com.rgarcia.w2m.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchSpaceShipParameters {
    int page;
    int limit;
    String query;
}
