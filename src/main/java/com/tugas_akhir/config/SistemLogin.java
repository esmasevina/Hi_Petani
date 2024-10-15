package com.tugas_akhir.config;

import com.tugas_akhir.collections.User;

import java.util.ArrayList;

public class SistemLogin {
    public static int userId;
    public static String username;

    public static int login(String username, String password){
        ArrayList<User> members = Getter.getAllMember();
        int a = 0;

        // checking username & password
        for (User member:members) {
            System.out.println(member.username);
            if(member.username.equalsIgnoreCase(username)){
                if(member.password.equals(password)){
                    userId = member.getId();
                    SistemLogin.username = member.username;
                    a = 1;
                    break;
                }else {
                    a = -1;
                }
            }else{
                a = 0;
            }
        }

        return a;
    }

    public static Boolean isLogin(){
        if(SistemLogin.userId != 0 && SistemLogin.username != null){
            return true;
        }else{
            return false;
        }
    }

}
