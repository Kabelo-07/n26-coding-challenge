package com.n26.api;

import com.n26.statistics.model.StatisticsModel;
import com.n26.statistics.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StatisticsResource {

    private final StatisticsService statisticsService;

    @GetMapping("/statistics")
    public ResponseEntity<StatisticsModel> getStatistics() {
        return ResponseEntity.ok(statisticsService.getStatistics());
    }
}
