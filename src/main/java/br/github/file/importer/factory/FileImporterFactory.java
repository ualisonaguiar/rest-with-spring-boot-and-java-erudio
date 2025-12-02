package br.github.file.importer.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import br.github.exception.BadRequestException;
import br.github.file.importer.contract.FileImporter;
import br.github.file.importer.impl.CsvImporter;
import br.github.file.importer.impl.XlsxImporter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class FileImporterFactory {

    @Autowired
    private ApplicationContext context;

    public FileImporter getImporter(String fileName) throws Exception {

        if (fileName.endsWith(".xlsx")) {
            return context.getBean(XlsxImporter.class);
        } else if (fileName.endsWith(".csv")) {
            return context.getBean(CsvImporter.class);
        } else {
            throw new BadRequestException("Invalid File Format!");
        }
    }
}
