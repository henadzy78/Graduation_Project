package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MilestoneBuilder {
    private String name;
    private String description;
}
