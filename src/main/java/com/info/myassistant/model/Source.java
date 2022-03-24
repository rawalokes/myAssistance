package com.info.myassistant.model;

import com.info.myassistant.dto.SourceDto;
import lombok.*;

import javax.persistence.*;

/**
 * @author rawalokes
 * Date:3/24/22
 * Time:12:21 AM
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "source")
public class Source {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "source_seq_gen")
    @SequenceGenerator(name = "source_seq_gen",sequenceName = "source_seq",allocationSize = 1)
    private Integer sourceId;

    private String sourceName;
    private String description;

    public Source(SourceDto sourceDto) {
        this.sourceId = sourceDto.getId();
        this.sourceName =sourceDto.getSourceName();
        this.description = sourceDto.getDescription();
    }
}
