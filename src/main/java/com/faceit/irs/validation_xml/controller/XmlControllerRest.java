package com.faceit.irs.validation_xml.controller;

import com.faceit.irs.validation_xml.dto.XmlValidResponse;
import com.faceit.irs.validation_xml.exception.RequestNoContentException;
import com.faceit.irs.validation_xml.exception.RequestUnsuportMediaTypeException;
import com.faceit.irs.validation_xml.service.XmlService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = {"/api/v1/xml"})
public class XmlControllerRest {

    private final XmlService xmlService;

    public XmlControllerRest(XmlService xmlService) {
        this.xmlService = xmlService;
    }

    @PostMapping(path = "/valid", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<XmlValidResponse> validationFile(@RequestParam("file") final MultipartFile file) {
        if (file.isEmpty()) {
            throw new RequestNoContentException("File is empty");
        }

        if (MediaType.APPLICATION_XML_VALUE.equals(file.getContentType())) {
            return ResponseEntity.ok(this.xmlService.validation(file));
        } else {
            throw new RequestUnsuportMediaTypeException("Unsupported media type");
        }
    }
}
