package com.bookadaisical;

public class LoginRequest {
    private String accountIdentificator;
    private String accountPassword;

    // Getters and setters (or Lombok annotations for automatic generation)

    public String getAccountIdentificator() {
        return accountIdentificator;
    }

    public void setAccountIdentificator(String accountIdentificator) {
        this.accountIdentificator = accountIdentificator;
    }

    public String getAccountPassword() {
        return accountPassword;
    }

    public void setAccountPassword(String accountPassword) {
        this.accountPassword = accountPassword;
    }
}
