package com.labanta.servidorlocal.ServicoService;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;


@Service

public class FileStorageService {
    private final String dirUploads = "uploads/images";

    public FileStorageService() {
        try {
            Files.createDirectories(Paths.get(dirUploads));
        } catch (IOException e) {
            throw new RuntimeException("Não foi possível criar a pasta de uploads!");
        }
}

    public String storeImage(MultipartFile file) {
        try {

            String uniqueName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();


            String outputPath = Paths.get(dirUploads).resolve(uniqueName).toString();


            Files.copy(file.getInputStream(), Paths.get(outputPath));


            return uniqueName;

        } catch (Exception ex) {
            throw new RuntimeException("Erro ao carregar ficheiro:" + ex.getMessage());
        }
    }
}
