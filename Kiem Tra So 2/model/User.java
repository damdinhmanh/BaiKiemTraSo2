package model;

public class User {
    private String userName;
    private String emailAdress;
    private String passwordLogin;

    public User(String userName, String emailAdress, String passwordLogin) {
        this.userName = userName;
        this.emailAdress = emailAdress;
        this.passwordLogin = passwordLogin;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getPasswordLogin() {
        return passwordLogin;
    }

    public void setPasswordLogin(String passwordLogin) {
        this.passwordLogin = passwordLogin;
    }

    @Override
    public String toString() {
        return "User [emailAdress=" + emailAdress + ", passwordLogin=" + passwordLogin + ", userName=" + userName + "]";
    }    
}
