package com.zfx.account.bean;

public class UserRightCheatLog implements java.io.Serializable {

	private static final long serialVersionUID = -3636141874686450559L;

    private String userId;

    private Short userCheatState;

    private String logTime;

    private Short logState;

    public UserRightCheatLog() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Short getUserCheatState() {
        return userCheatState;
    }

    public void setUserCheatState(Short userCheatState) {
        this.userCheatState = userCheatState;
    }

    public String getLogTime() {
        return logTime;
    }

    public void setLogTime(String logTime) {
        this.logTime = logTime;
    }

    public Short getLogState() {
        return logState;
    }

    public void setLogState(Short logState) {
        this.logState = logState;
    }
}