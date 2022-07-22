package api.EmployeesApi.controllers;

import api.EmployeesApi.models.Employee;
import api.EmployeesApi.models.ResponseObject;
import api.EmployeesApi.repositories.EmployeesRepository;
import api.EmployeesApi.services.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/profile/avatar")
@CrossOrigin("*")
public class FileUploadController {
    public final Path storageFolder = Paths.get("uploads");

    @Autowired
    private IStorageService storageService;

    @Autowired
    private EmployeesRepository employeesRepository;

    @PostMapping("/employeeNo={employeeNo}")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file, @PathVariable Integer employeeNo) {
        try {
            String generatedFileName = storageService.storeFile(file);

            // Delete old file if exists
            String oldAvatar = employeesRepository.findById(employeeNo).get().getAvatar();

            // Update new file
            Optional<Employee> updateEmployee = employeesRepository.findById(employeeNo)
                    .map(employee -> {
                        employee.setAvatar(generatedFileName);
                        return employeesRepository.save(employee);
                    });

            if (oldAvatar != null) {
                Path storedFilePath = this.storageFolder.resolve(
                        Paths.get(oldAvatar)).normalize().toAbsolutePath();
                Files.deleteIfExists(storedFilePath);
            }

            if (updateEmployee.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject("ok", "upload file successfully", generatedFileName)
                );
            }
            else {
                return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                        new ResponseObject("failed", "Cant find employee", "")
                );
            }
        } catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
                    new ResponseObject("failed", exception.getMessage(), "")
            );
        }
    }

    @GetMapping("/files/{fileName:.+}")
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(bytes);
        } catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }

    // How to load all uploaded files
    @GetMapping("")
    public ResponseEntity<ResponseObject> getUploadedFiles() {
        try {
            List<String> urls = storageService.loadAll()
                    .map(path -> {
                        // convert fileName to url(send request "readDetailFile")
                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
                        return urlPath;
                    }).collect(Collectors.toList());
            return ResponseEntity.ok(new ResponseObject("ok", "List files successfully", urls));
        } catch (Exception exception) {
            return ResponseEntity.ok(new ResponseObject("failed", "List files failed", new String[] {}));
        }
    }
}
