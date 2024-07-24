package com.ojt_server.modules.image.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageModelDTO {
    private Long id;
    @NotBlank(message = "src is required")
    private String src;
}
