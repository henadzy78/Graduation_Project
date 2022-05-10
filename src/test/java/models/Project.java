package models;

import enums.ProjectType;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Project {
    private String name;
    private String announcement;
    private boolean isShowAnnouncement;
    private ProjectType typeOfProject;
    private boolean isCompleted;
}