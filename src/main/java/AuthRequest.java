public class AuthRequest {
    private String password;
    private String screenHeight;
    private String screenWidth;
    private boolean touchStartSupported;
    private String userName;

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScreenHeight(String screenHeight) {
        this.screenHeight = screenHeight;
    }

    public void setScreenWidth(String screenWidth) {
        this.screenWidth = screenWidth;
    }

    public void setTouchStartSupported(boolean touchStartSupported) {
        this.touchStartSupported = touchStartSupported;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public String getScreenHeight() {
        return screenHeight;
    }

    public String getScreenWidth() {
        return screenWidth;
    }

    public boolean getTouchStartSupported() {
        return touchStartSupported;
    }

    public String getUserName() {
        return userName;
    }
}
