package view;

import java.util.List;

public interface IUserInterface<T, L> {
    void init();
    void insert(T obj, List<T> objList);
    void delete(T obj, List<T> objList);
    void updateUserName(String currentUserName, String changeUserName);
    void updateEmail(String currentUserName, String changeEmail);
    void updatePassword(String currentUserName, String changePassword);
    void display(List<T> objList);
    boolean isExistedUser(String checkUserName);
    boolean isExistedEmail(String checkUserName);
    String getUserPassWord(String checkUserName);
    void forgotPassword(String inputEmail, String newPassword);
}
