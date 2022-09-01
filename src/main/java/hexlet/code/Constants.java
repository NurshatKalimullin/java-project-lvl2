package hexlet.code;

public interface Constants {

    interface Statuses {
        void printStatus();
        String CHANGED = "changed";
        String UNCHANGED = "unchanged";
        String DELETED = "deleted";
        String ADDED = "added";
    }

    interface Formats {
        void printFormat();
        String STYLISH = "stylish";
        String PLAIN = "plain";
        String JSON = "json";
        String YML = "yml";
    }


}
