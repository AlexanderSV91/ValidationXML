package com.faceit.irs.validation_xml.service;

import com.faceit.irs.validation_xml.dto.XmlValidResponse;
import org.springframework.web.multipart.MultipartFile;

public interface XmlService {

    XmlValidResponse validation(final MultipartFile file);
}
