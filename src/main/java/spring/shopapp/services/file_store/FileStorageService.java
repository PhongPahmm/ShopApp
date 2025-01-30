package spring.shopapp.services.file_store;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path rootLocation;
    public static final String UPLOAD_DIR = "uploads"; // Thêm hằng số

    public FileStorageService() {
        this.rootLocation = Paths.get(UPLOAD_DIR).toAbsolutePath().normalize();
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage directory", e);
        }
    }

    public String store(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("Failed to store empty file.");
        }
        try {
            // Xử lý tên file: thay khoảng trắng bằng gạch dưới
            String originalFileName = StringUtils.cleanPath(
                    Objects.requireNonNull(file.getOriginalFilename())
                            .replace(" ", "_") // Thay thế khoảng trắng
                            .replaceAll("[^a-zA-Z0-9._-]", "") // Loại bỏ ký tự đặc biệt
            );

            String uniqueFileName = UUID.randomUUID() + "_" + originalFileName;
            Path targetLocation = this.rootLocation.resolve(uniqueFileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            return uniqueFileName;
        } catch (IOException ex) {
            throw new RuntimeException("Failed to store file " + file.getOriginalFilename(), ex);
        }
    }
}