package Todos;

import Todos.Task;

public class SimpletTask extends Task {

    protected String title;

    public SimpletTask(int id, String title) {
        super(id);
        this.title=title;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public boolean matches(String query) {
        if (title.contains(query)) {
            return true;
        }

        return false;
    }

}
