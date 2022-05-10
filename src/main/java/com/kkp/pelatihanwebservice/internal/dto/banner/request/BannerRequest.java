package com.kkp.pelatihanwebservice.internal.dto.banner.request;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

public class BannerRequest {
    @NotEmpty(message = "Title is required")
    private String title;

    @NotEmpty(message = "Image is required")
    private String image;

    LocalDateTime createdAt = LocalDateTime.now();
    LocalDateTime updatedAt = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
