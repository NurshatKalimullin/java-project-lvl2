package hexlet.code;

public final class Change {

    private final Object oldValue;
    private final Object newValue;
    private final String status;

    public Change(Object oldestValue, Object newestValue, String statuses) {
        this.oldValue = oldestValue;
        this.newValue = newestValue;
        this.status = statuses;
    }

    public Object getOldValue() {
        return oldValue;
    }

    public Object getNewValue() {
        return newValue;
    }

    public String getStatus() {
        return status;
    }

}
