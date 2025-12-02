package br.github.file.importer.impl;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import br.github.data.dto.v1.PersonDTO;
import br.github.file.importer.contract.FileImporter;

@Component
public class XlsxImporter implements FileImporter {

    @Override
    public List<PersonDTO> importFiles(InputStream inputStream) throws Exception {

        try (XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {
            XSSFSheet sheet = workbook.getSheetAt(0); // valor da aba

            Iterator<Row> rowInIterator = sheet.iterator();

            if (rowInIterator.hasNext()) {
                rowInIterator.next();
            }

            return parseRowsToPersonDTOList(rowInIterator);
        }
    }

    private List<PersonDTO> parseRowsToPersonDTOList(Iterator<Row> records) {

        List<PersonDTO> peoples = new ArrayList<>();

        while (records.hasNext()) {
            Row row = records.next();

            if (isRowValid(row)) {
                peoples.add(parseRowToPersonsDTO(row));
            }
        }

        return peoples;
    }

    private PersonDTO parseRowToPersonsDTO(Row row) {

        PersonDTO person = new PersonDTO();
        person.setFirstName(row.getCell(0).getStringCellValue());
        person.setLastName(row.getCell(1).getStringCellValue());
        person.setAddress(row.getCell(2).getStringCellValue());
        person.setGender(row.getCell(3).getStringCellValue());
        person.setSituacao(true);

        return person;
    }

    private static boolean isRowValid(Row row) {
        return row.getCell(0) != null && row.getCell(0).getCellType() != CellType.BLANK;
    }

}
