package com.faceit.irs.validation_xml.service.impl;

import com.faceit.irs.validation_xml.dto.XmlValidResponse;
import com.faceit.irs.validation_xml.exception.FileNotFoundException;
import com.faceit.irs.validation_xml.exception.ValidationException;
import com.faceit.irs.validation_xml.service.XmlService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.annotation.PostConstruct;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class XmlServiceImpl implements XmlService {

    private static final String REF_XSD_PATH = "src/main/resources/2020v3.0/Extensions/2350/Return2350.xsd";

    private Schema xsdSchema;

    @PostConstruct
    private void init() {
        final File xsdSchemaFile = new File(REF_XSD_PATH);
        if (xsdSchemaFile.exists()) {
            try {
                final SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
                this.xsdSchema = factory.newSchema(xsdSchemaFile);
            } catch (SAXException e) {
                e.printStackTrace();
            }
        } else {
            throw new FileNotFoundException("File with extension .xsd not found");
        }
    }

    @Override
    public XmlValidResponse validation(final MultipartFile file) {
        boolean isValid = true;
        try {
            final Validator validator = xsdSchema.newValidator();
            validator.validate(new StreamSource(file.getInputStream()));
        } catch (IOException e) {
            isValid = false;
        } catch (SAXException e) {
            final SAXParseException saxParseException = (SAXParseException) e;
            throw new ValidationException(saxParseException.getMessage(),
                    saxParseException.getLineNumber(), saxParseException.getColumnNumber());
        }
        return new XmlValidResponse(file.getOriginalFilename(), isValid, LocalDateTime.now());
    }
}
