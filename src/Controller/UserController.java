/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author msi1
 */
public class UserController {
     private static int loggedInUserId;
      private static int loggedInUserUserName;

    public static int getLoggedInUserUserName() {
        return loggedInUserUserName;
    }

    public static void setLoggedInUserUserName(int loggedInUserUserName) {
        UserController.loggedInUserUserName = loggedInUserUserName;
    }

    public static int getLoggedInUserId() {
        return loggedInUserId;
    }

    public static void setLoggedInUserId(int userId) {
        loggedInUserId = userId;
    }
    
}
