package utils;

public class Endpoints {
    public static final String GET_PROJECT = "/index.php?/api/v2/get_project/{project_id}";
    public static final String ADD_PROJECT = "/index.php?/api/v2/add_project";
    public static final String UPDATE_PROJECT = "/index.php?/api/v2/update_project/{project_id}";
    public static final String DELETE_PROJECT = "/index.php?/api/v2/delete_project/{project_id}";
    public static final String ADD_MILESTONE = "/index.php?/api/v2/add_milestone/{project_id}";
    public static final String GET_MILESTONE = "/index.php?/api/v2/get_milestone/{milestone_id}";
    public static final String ADD_SECTION = "/index.php?/api/v2/add_section/{project_id}";
    public static final String GET_CASE = "/index.php?/api/v2/get_case/{case_id}";
    public static final String ADD_CASE = "/index.php?/api/v2/add_case/{section_id}";
    public static final String GET_USER_EMAIL = "index.php?/api/v2/get_user_by_email&email={email}";
}
