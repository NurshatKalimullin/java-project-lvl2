package hexlet.code;

public final class Changes {

    private final Object oldValue;
    private final Object newValue;
    private final String status;

    public Changes(Object oldestValue, Object newestValue, String statuses) {
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
