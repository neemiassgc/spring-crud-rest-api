package web.spring.crud.service;

public final class Fallback {

    private boolean success;
    private String message;

    public Fallback(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Fallback() {
        this(false, "");
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
