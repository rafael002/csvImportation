package org.example.infrastructure.filters;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FilenameFilter;
@Component
public class CSVFilenameFilter implements FilenameFilter {
    @Override
    public boolean accept(File f, String name) {
        return name.endsWith(".csv");
    }
}
