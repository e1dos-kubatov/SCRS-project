package comsep23.srcspro.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;


@Service
public class FileStorageService {

    private static final Path ROOT =
            Paths.get("src/main/resources/static/uploads");

    public String save(MultipartFile file) {
        try {
            Files.createDirectories(ROOT);

            String fileName =
                    System.currentTimeMillis() + "_" + file.getOriginalFilename();

            Files.copy(file.getInputStream(), ROOT.resolve(fileName),
                    StandardCopyOption.REPLACE_EXISTING);

            return fileName;

        } catch (IOException e) {
            throw new RuntimeException("File upload failed", e);
        }
    }
}