package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SectionBuilder {
    private String name;
    private String description;
}
