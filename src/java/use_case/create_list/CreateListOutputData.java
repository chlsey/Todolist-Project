package use_case.create_list;

public class CreateListOutputData {
    private String title;

    public CreateListOutputData(String listTitle) {
        this.title = listTitle;
    }

    public String getTitle() {
        return this.title;
    }
}
