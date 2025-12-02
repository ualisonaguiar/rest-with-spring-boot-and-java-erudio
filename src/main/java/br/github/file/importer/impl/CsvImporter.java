package br.github.file.importer.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Component;

import br.github.data.dto.v1.PersonDTO;
import br.github.file.importer.contract.FileImporter;

@Component
public class CsvImporter implements FileImporter {

    @Override
    public List<PersonDTO> importFiles(InputStream inputStream) throws Exception {

        CSVFormat format = CSVFormat.Builder.create()
                .setHeader()
                .setSkipHeaderRecord(true)
                .setIgnoreEmptyLines(true)
                .setTrim(true)
                .build();

        Iterable<CSVRecord> records = format.parse(new InputStreamReader(inputStream));

        return parseRecordsToPersonsDTOs(records);
    }

    private List<PersonDTO> parseRecordsToPersonsDTOs(Iterable<CSVRecord> records) {

        List<PersonDTO> peoples = new ArrayList<>();

        for (CSVRecord record : records) {
            PersonDTO person = new PersonDTO();
            person.setFirstName(record.get("first_name"));
            person.setLastName(record.get("last_name"));
            person.setAddress(record.get("address"));
            person.setGender(record.get("gender"));
            person.setSituacao(true);
            peoples.add(person);
        }

        return peoples;
    }

}
