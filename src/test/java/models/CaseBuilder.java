package models;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CaseBuilder {
    private String title;
    @SerializedName(value = "type_id")
    private int typeID;
}
