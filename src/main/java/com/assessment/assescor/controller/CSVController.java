package com.assessment.assescor.controller;

import com.assessment.assescor.entity.Person;
import com.assessment.assescor.service.CSVService;
import com.assessment.assescor.ResponseMessage;
import com.assessment.assescor.helper.CSVHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@CrossOrigin("http://localhost:8081")
@RestController
@RequestMapping(path = "/api/csv")
public class CSVController {

    private static final String UPLOAD_FILE_MESSAGE = "Please upload a csv file!";

    private static final String UPLOAD_SUCCESSFUL = "Uploaded the file successfully: ";

    private static final String COULD_NOT_UPLOAD = "Could not upload the file: ";

    private final CSVService fileService;

    @Autowired
    public CSVController(CSVService fileService) {
        this.fileService = fileService;
    }


    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
        String message = "";

        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileService.save(file);

                message = UPLOAD_SUCCESSFUL + file.getOriginalFilename();
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            } catch (Exception e) {
                message =  COULD_NOT_UPLOAD + file.getOriginalFilename() + "!" + e.getMessage();
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
            }
        }

        message = UPLOAD_FILE_MESSAGE;
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
    }


    @GetMapping("/download")
    public ResponseEntity<Resource> getFile() {
        String filename = "tutorials.csv";
        InputStreamResource file = new InputStreamResource(fileService.load());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + filename)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(file);
    }
}