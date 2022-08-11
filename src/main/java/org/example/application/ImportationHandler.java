package org.example.application;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.domain.importation.services.ImportationService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class ImportationHandler {
    private final ImportationService importationService;

    @Scheduled(fixedDelay = 5000)
    public void handle() {
        importationService.runImportation();
    }
}
