package controller;

import model.*;
import utils.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static Scanner scanner;
    public static LoginService loginService;
    public static List<User> listUser;

    public static void openScanner() {
        scanner = new Scanner(System.in);
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static void optionGuide() {
        System.out.println("");
        System.out.println("");
        System.out.println("Input number 1: Login");
        System.out.println("Input number 2: Register New Account");
        System.out.println("Input number 3: Exit Programe");
        System.out.printf("\n\nPlease input number: ");
    }

    public static void initLoginService() {
        loginService = new LoginService();    
        loginService.initLoginServiceModel();

        listUser = new ArrayList<User>();
    }

    public static void loginOpionHandler() {
        scanner.nextLine();
        while (true) {
            System.out.printf("\n\nInput login UserName: ");
            String loginUser = scanner.nextLine();

            System.out.printf("\nInput login Password: ");
            String loginPassword = scanner.nextLine();

            int retLogin = loginService.loginAccount(loginUser, loginPassword, listUser);

            
            if (retLogin == ConstantVar.RET_LOGIN_SUCCESS) {
                    System.out.println("Welcome user: " + loginUser + "!!!");

                    while (true) {
                        System.out.println("Please select option below:");
                        System.out.println("Input number 1: Change Username");
                        System.out.println("Input number 2: Change Email");
                        System.out.println("Input number 3: Change Password");
                        System.out.println("Input number 4: Log-out");
                        System.out.println("Input number 0: Exit Program!!!");

                        System.out.printf("Input option: ");
                        int loginSucesswOption = scanner.nextInt();

                        switch (loginSucesswOption) {
                            case 1: {
                                scanner.nextLine();
                                System.out.printf("Input new UserName: ");
                                String newUserName = scanner.nextLine();
                                loginService.changeUserName(loginUser, newUserName);
                                loginUser = newUserName;

                                //DEBUGGING OUTPUT
                                loginService.showAllUserInfor(listUser);
                                break;
                            }

                            case 2: {
                                scanner.nextLine();
                                System.out.printf("Input new Email: ");
                                String newEmail = scanner.nextLine();
                                loginService.changeEmail(loginUser, newEmail);
                                
                                //DEBUGGING OUTPUT
                                loginService.showAllUserInfor(listUser);
                                break;                            
                            }
                            case 3: {
                                scanner.nextLine();
                                System.out.printf("Input new Password: ");
                                String newPassword = scanner.nextLine();
                                loginService.changePassword(loginUser, newPassword);

                                //DEBUGGING OUTPUT
                                loginService.showAllUserInfor(listUser);
                                break;                            
                            }
                            case 4: {
                                //goto login register page
                                loginRegisterPage();
                                break;
                            }
                            case 0: {
                                //EXIT PROGRAME
                                exitProgramOptionHandler();
                            }

                        }
                    }

            } else if (retLogin == ConstantVar.RET_PASSWORD_INVALID) {
                    System.out.println("Wrong Password-> please select option below:");
                    System.out.println("Input number 1: Select login again");
                    System.out.println("Input number 2: Select forgot password");

                    System.out.printf("Input option: ");
                    int loginWrongPWOption = scanner.nextInt();

                    if (loginWrongPWOption == ConstantVar.WRONG_PASSWORD_LOGIN_AGAIN) {
                        System.out.println("Please login again!");
                        scanner.nextLine();
                    } else if (loginWrongPWOption == ConstantVar.WRONG_PASSWORD_FORGOT_PASSWORD) {
                        forgotPasswordHandler();
                    } 
            } else if (retLogin == ConstantVar.RET_USERNAME_INVALID) {
                    System.out.println("Wrong UserName-> please login again!");
            }
        }
    }

    public static void registerAccountOptionHandler() {
        scanner.nextLine();
        System.out.printf("\n\nInput register UserName: ");
        String regUserName = scanner.nextLine();

        System.out.printf("\nInput register Password: ");
        String regPassword = scanner.nextLine();

        System.out.printf("\nInput register Email: ");
        String regEmail = scanner.nextLine();

        loginService.registerAccount(regUserName, regPassword, regEmail, listUser);

        loginService.showAllUserInfor(listUser);
    }

    public static void exitProgramOptionHandler() {
        System.out.println("Exited Program, Good Bye^^");
        closeScanner();
        System.exit(0);
    }

    public static void loginAgainHandler() {
        System.out.println("Login Again:");
        //registerAccountOptionHandler();
    }

    public static void forgotPasswordHandler() {
        scanner.nextLine();
        System.out.printf("\n\nInput your registered Email: ");
        String forgotUserEmail = scanner.nextLine();

        if (loginService.isEmailExistedModel(forgotUserEmail)) {
            System.out.printf("\nInput new password to reset: ");
            String inputNewPass = scanner.nextLine();
            loginService.forgotPassword(forgotUserEmail, inputNewPass);
            
            System.out.println("Reset password by Email successfull!!!");
            //DEBUG
            loginService.showAllUserInfor(listUser);

            loginRegisterPage();
        } else {
            System.out.println("Email account not existed, cannot recover password");
        }
    }

    public static void loginRegisterPage() {
        int userOption = 0;

        while(true) { 
            if (userOption == 4) {
                break;
            }

            //show option to select
            optionGuide();

            userOption = scanner.nextInt();

            switch (userOption) {
                case ConstantVar.OPT_LOGIN: {
                    loginOpionHandler();
                    break;
                }
                case ConstantVar.OPT_REGISTER_ACCOUNT: {
                    registerAccountOptionHandler();
                    break;
                }
                case ConstantVar.OPT_EXIT_PROGRAME: {
                    exitProgramOptionHandler();
                    break;
                }
                default: {
                    System.out.println("Wrong input option, please input option again!");
                }

            }            
        }
    }

    public static void main(String[] args) {
        //open scanner
        openScanner();

        //init login service
        initLoginService();

        //login register loop;
        loginRegisterPage();

    }
}
