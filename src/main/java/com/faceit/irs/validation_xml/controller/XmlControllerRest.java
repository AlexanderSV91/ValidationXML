package com.faceit.irs.validation_xml.controller;

import com.faceit.irs.validation_xml.dto.XmlValidResponse;
import com.faceit.irs.validation_xml.exception.RequestNoContentException;
import com.faceit.irs.validation_xml.exception.RequestUnsuportMediaTypeException;
import com.faceit.irs.validation_xml.service.XmlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/api/v1/xml"})
@RequiredArgsConstructor
public class XmlControllerRest {

    private final XmlService xmlService;

    @PostMapping(path = "/valid", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<XmlValidResponse> validationFile(@RequestParam("file") final MultipartFile file) {
        if (file.isEmpty()) {
            throw new RequestNoContentException("File is empty");
        } else {
            if (MediaType.APPLICATION_XML_VALUE.equals(file.getContentType())) {
                XmlValidResponse xmlValidResponse = xmlService.validation(file);
                return ResponseEntity.status(HttpStatus.OK).body(xmlValidResponse);
            } else {
                throw new RequestUnsuportMediaTypeException("Unsupported media type");
            }
        }
    }
}
