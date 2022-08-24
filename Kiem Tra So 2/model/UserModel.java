package model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import view.IUserInterface;

public class UserModel implements IUserInterface<User, List<User>>{
    private Map<String, String> mapUserPassword; //UserName-Password mapping
    private Map<String, User> mapUserObject; //UserName-User Object mapping
    private Map<String, User> mapEmailObject; //Email-User Object Mapping

    @Override
    public void init() {
        mapUserPassword = new HashMap<String, String>();
        mapUserObject = new HashMap<String, User>();
        mapEmailObject = new HashMap<String, User>();
    }

    @Override
    public void insert(User obj, List<User> objList) {
        if (mapUserPassword.containsKey(obj.getUserName())) {
            System.out.println("This UserName already existed, cannot register this user");
            return;
        }

        if (mapEmailObject.containsKey(obj.getEmailAdress())) {
            System.out.println("This Email already existed, cannot register this user");
            return;
        }

        objList.add(obj);
        mapUserPassword.put(obj.getUserName(), obj.getPasswordLogin());
        mapUserObject.put(obj.getUserName(), obj);
        mapEmailObject.put(obj.getEmailAdress(), obj);
    }

    @Override
    public void delete(User obj, List<User> objList) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void display(List<User> objList) {
        for (User user : objList) {
            System.out.println(user.toString());
        }   
    }

    @Override
    public boolean isExistedUser(String checkUserName) {
       return mapUserPassword.containsKey(checkUserName);
    }

    @Override
    public String getUserPassWord(String checkUserName) {
        return mapUserPassword.get(checkUserName);
    }

    @Override
    public void updateUserName(String currentUserName, String changeUserName) {

        if (mapUserPassword.containsKey(changeUserName)) {
            System.out.println("This UserName already existed, cannot update");
            return;
        }

        User updateUser = mapUserObject.get(currentUserName);
        updateUser.setUserName(changeUserName);

        mapUserPassword.remove(currentUserName);
        mapUserPassword.put(changeUserName, updateUser.getPasswordLogin());

        mapUserObject.remove(currentUserName);
        mapUserObject.put(changeUserName, updateUser);
    }

    @Override
    public void updateEmail(String currentUserName, String changeEmail) {
        if (mapEmailObject.containsKey(changeEmail)) {
            System.out.println("This Email already existed, cannot update");
            return;
        }

        User updateUser = mapUserObject.get(currentUserName);
        updateUser.setEmailAdress(changeEmail);
        
        mapEmailObject.remove(updateUser.getEmailAdress());
        mapEmailObject.put(changeEmail, updateUser);
    }

    @Override
    public void updatePassword(String currentUserName, String changePassword) {
        User updateUser = mapUserObject.get(currentUserName);
        updateUser.setPasswordLogin(changePassword);

        mapUserPassword.remove(currentUserName);
        mapUserPassword.put(currentUserName, updateUser.getPasswordLogin());

        mapUserObject.remove(currentUserName);
        mapUserObject.put(currentUserName, updateUser);
    }

    @Override
    public void forgotPassword(String inputEmail, String newPassword) {
        User curUser = mapEmailObject.get(inputEmail);
        curUser.setPasswordLogin(newPassword);
    }

    @Override
    public boolean isExistedEmail(String checkEmail) {
        return  mapEmailObject.containsKey(checkEmail);
    }  
}
