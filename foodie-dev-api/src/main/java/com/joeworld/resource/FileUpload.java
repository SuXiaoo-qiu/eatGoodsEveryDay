package com.joeworld.resource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "file")
@PropertySource("classpath:file-upload-dev.properties")
public class FileUpload {
    private String imageUserFaceLocation;
    private String imgServiceUrl;

    public String getImgServiceUrl() {
        return imgServiceUrl;
    }

    public void setImgServiceUrl(String imgServiceUrl) {
        this.imgServiceUrl = imgServiceUrl;
    }

    public String getImageUserFaceLocation() {
        return imageUserFaceLocation;
    }

    public void setImageUserFaceLocation(String imageUserFaceLocation) {
        this.imageUserFaceLocation = imageUserFaceLocation;
    }
}
