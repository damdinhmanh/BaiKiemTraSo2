package model;

import java.util.List;
import utils.*;

public class LoginService {
    private UserModel userModel;
    
    public void initLoginServiceModel() {
        userModel = new UserModel();
        userModel.init();
    }

    public int loginAccount(String loginUserName, String loginPassword, List<User> objList) {
        System.out.printf("\n\nYou are in: Login page\n\n");

        if (userModel.isExistedUser(loginUserName) == false) {
            return ConstantVar.RET_USERNAME_INVALID;
        }

        //Check password invalid for user;
        if (userModel.getUserPassWord(loginUserName).equals(loginPassword)) {
            return ConstantVar.RET_LOGIN_SUCCESS;
        }

        return ConstantVar.RET_PASSWORD_INVALID;
    }

    public void registerAccount(String regUserName, String regPassword, String regEmail, List<User> objList) {
        System.out.printf("\n\nYou are in: Register New Account Page\n\n");

        //create new User
        User user = new User(regUserName, regEmail, regPassword);
        userModel.insert(user, objList);
    }

    public boolean isEmailExistedModel(String forgotEmail) {
        return userModel.isExistedEmail(forgotEmail);
    }

    public void forgotPassword(String forgotUserEmail, String newPassword) {
        System.out.printf("\n\nYou are in: Forgot Password Page\n\n");

        userModel.forgotPassword(forgotUserEmail, newPassword);
    }

    //!!!FOR DEBUGGING ONLY!!! NEED REVERT///
    public void showAllUserInfor(List<User> objList) {
        userModel.display(objList);
    }

    public void changeUserName(String currentUserName, String changeUserName) {
        userModel.updateUserName(currentUserName, changeUserName);
    }

    public void changeEmail(String currentUserName, String changeEmail) {
        userModel.updateEmail(currentUserName, changeEmail);
    }

    public void changePassword(String currentUserName, String changePassword) {
        userModel.updatePassword(currentUserName, changePassword);
    }
}
