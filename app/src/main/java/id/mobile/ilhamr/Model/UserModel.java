package id.mobile.ilhamr.Model;

public class UserModel {

    String userName, userPassword, userEmail, userPhoneNumber;

//    public User(String userName, String userPassword, String userEmail, String userPhoneNumber){
//        this.userName = userName;
//        this.userPassword = userPassword;
//        this.userEmail = userEmail;
//        this.userPhoneNumber = userPhoneNumber;
//    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhoneNumber() {
        return userPhoneNumber;
    }

    public void setUserPhoneNumber(String userPhoneNumber) {
        this.userPhoneNumber = userPhoneNumber;
    }


    @Override
    public String toString() {
        return "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", userPhoneNumber='" + userPhoneNumber + '\'' +
                '}';
    }
}
