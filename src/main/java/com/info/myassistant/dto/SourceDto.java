package com.info.myassistant.dto;

import com.info.myassistant.model.Source;
import lombok.*;

import javax.validation.constraints.*;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:12:28 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SourceDto {
    private Integer id;
    @NotEmpty(message = "Source cannot be Empty")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Source cannot be Empty")
    private String source;

    @NotEmpty(message = "Description cannot be Empty")
    @Pattern(regexp = "\\A(?!\\s*\\Z).+",message = "Description cannot be Empty")
    private String description;

    public SourceDto(Source source) {
        this.id = source.getId();
        this.source= source.getSource();
        this.description = source.getDescription();
    }
}
