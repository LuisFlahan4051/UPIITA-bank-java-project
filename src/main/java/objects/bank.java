package objects;

import crud.userCRUD;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

public class bank {
    userCRUD crud;

    bank (){
        crud = new userCRUD("bank_upiita_db");
    }
    public bank(String databaseName){
        crud = new userCRUD(databaseName);
    }

    public void createACard(String account, String path, String filename){
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(path+"/"+filename+".card"));
            writer.write(account);
            writer.close();
        }catch (Exception e){
            System.out.println("Can't write your card");
        }
    }

    public String readCard(String path){
        String account = null;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(path));
            account = reader.readLine();
            reader.close();
        }catch (Exception e){
            System.out.println("No card found");
        }
        return account;
    }

    public boolean makeUser(user newUser){
        return crud.saveOne(newUser);
    }

    public user validateUser(String account){
        return crud.getByAccount(account);
    }
    public user validateUser(String username, String password){
        return crud.getByUsername(username, password);
    }

    public void increase(user user, double cash){
        user.increase(cash);
        crud.updateOne(user);
    }

    public boolean decrease(user user, double cash){
        if(user.getContent()>0 && cash<=user.getContent()){
            user.decrease(cash);
            crud.updateOne(user);
            return true;
        }else{
            return false;
        }
    }
}
