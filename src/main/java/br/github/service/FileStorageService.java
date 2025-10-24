package br.github.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import br.github.config.FileStorageConfig;
import br.github.exception.FileNotFoundException;
import br.github.exception.FileStorageException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class FileStorageService {

    private Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageConfig fileStorageConfig) {
        Path path = Paths.get(fileStorageConfig.getUploadDir()).toAbsolutePath()
                .toAbsolutePath().normalize();

        this.fileStorageLocation = path;
        try {
            log.info("Creating Directories");
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception e) {
            log.error("Could not create the directory where files will be stored!");
            throw new FileStorageException("Could not create the directory where files will be stored!", e);
        }
    }

    public String storageFile(MultipartFile file) {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            if (fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename Contains a Invalid path Sequence " + fileName);
            }

            log.info("Saving file in Disk: " + fileName);

            Files.copy(file.getInputStream(), this.fileStorageLocation.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (Exception e) {
            String message = "Could not store file " + fileName + ". Please try Again!";
            log.error(message, e);
            throw new FileStorageException(message, e);
        }
    }

    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                log.error("File not found " + fileName);
                throw new FileNotFoundException("File not found " + fileName);
            }
        } catch (Exception e) {
            log.error("File not found " + fileName);
            throw new FileNotFoundException("File not found " + fileName, e);
        }
    }
}
