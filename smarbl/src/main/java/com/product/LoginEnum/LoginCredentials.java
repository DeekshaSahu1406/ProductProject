package com.product.LoginEnum;

public enum LoginCredentials {

    USER1("admin@gmail.com", "admin");


    private final String loginId;
    private final String password;

    LoginCredentials(String loginId, String password) {
        this.loginId = loginId;
        this.password = password;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getPassword() {
        return password;
    }
}
