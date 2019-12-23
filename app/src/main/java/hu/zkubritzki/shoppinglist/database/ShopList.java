package hu.zkubritzki.shoppinglist.database;

public class ShopList {

    private Integer ListId;
    private String Name;
    private boolean completed;

    public ShopList() {
    }

    public Integer getListId() {
        return ListId;
    }

    public void setListId(Integer listId) {
        ListId = listId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
