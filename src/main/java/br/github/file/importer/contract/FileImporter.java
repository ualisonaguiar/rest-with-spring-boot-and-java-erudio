package br.github.file.importer.contract;

import java.io.InputStream;
import java.util.List;

import br.github.data.dto.v1.PersonDTO;

public interface FileImporter {
    
    List<PersonDTO> importFiles(InputStream inputStream) throws Exception;

}
