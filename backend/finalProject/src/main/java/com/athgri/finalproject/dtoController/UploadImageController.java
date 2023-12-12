package com.athgri.finalproject.dtoController;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Value;

import static com.athgri.finalproject.utilities.Log.logInfo;

/**
 * The UploadImageController class handles file upload functionality.
 */
@Tag(name = "Image Upload API")
@RestController
public class UploadImageController {

    @Value("${upload.storage.path}")
    private String uploadPath;

    /**
     * Handles file upload requests.
     *
     * @param file the MultipartFile representing the uploaded file
     * @return ResponseEntity indicating the result of the file upload operation
     */
    @Operation(summary = "Upload Image", description = "Upload the specified image file to the server")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully uploaded"),
            @ApiResponse(responseCode = "404", description = "Error failed to upload file"),
            @ApiResponse(responseCode = "500", description = "Internal Server error")
    })
    @CrossOrigin
    @PostMapping("api/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {

            Path uploadDir = Paths.get(uploadPath);

            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            Path filePath = uploadDir.resolve(file.getOriginalFilename());
            logInfo("UploadImageController::api/upload", "Filepath is: " + filePath);

            Files.copy(file.getInputStream(), filePath);

            return ResponseEntity.ok("File uploaded successfully: " + filePath.toString());
        }
        catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }
}