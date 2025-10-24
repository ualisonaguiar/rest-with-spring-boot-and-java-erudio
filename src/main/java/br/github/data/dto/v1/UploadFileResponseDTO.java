package br.github.data.dto.v1;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class UploadFileResponseDTO {

    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
}
