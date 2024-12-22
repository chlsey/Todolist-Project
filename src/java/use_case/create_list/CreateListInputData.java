package use_case.create_list;

public class CreateListInputData {
    private String listTitle;

    public CreateListInputData(String listTitle) {
        this.listTitle = listTitle;
    }

    public String getListTitle() {
        return this.listTitle;
    }
}
