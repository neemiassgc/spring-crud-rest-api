package web.spring.crud.entity;

public final class Fallback {

    private boolean success;
    private String message;

    public Fallback() {
        this.success = true;
        this.message = "";
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
