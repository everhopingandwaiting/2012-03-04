package qa.service;

public class PrivilegeCheckingResult {
    private boolean passed;
    private String message;

    public PrivilegeCheckingResult(boolean passed, String message) {
        this.passed = passed;
        this.message = message;
    }

    public static PrivilegeCheckingResult getPassedResult() {
        return new PrivilegeCheckingResult(true, "OK");
    }

    public static PrivilegeCheckingResult getNotPassedResult(String message) {
        return new PrivilegeCheckingResult(false, "Privilege Checking Failed: " + message);
    }

    public String getMessage() {
        return message;
    }

    public boolean isPassed() {
        return passed;
    }
}
