package hexlet.code;

public interface MyConstants {

    public void printStatus();

    interface Statuses {
        String CHANGED = "changed";
        String UNCHANGED = "unchanged";
        String DELETED = "deleted";
        String ADDED = "added";
    }

    interface Formats {
        String STYLISH = "stylish";
        String PLAIN = "plain";
        String JSON = "json";
        String YML = "yml";
    }


}
