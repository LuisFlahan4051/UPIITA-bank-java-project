package luisflahan4051;


import com.formdev.flatlaf.FlatDarculaLaf;
import objects.bank;
import objects.user;
import views.*;

import javax.swing.*;

public class Main {
    private static bank bank = new bank("bank_upiita_db");
    static private user currentUser = new user();

    public static void main(String[] args) {
        database.database.testConnectionToDB("bank_upiita_db");
        init();
        loginForm();
    }

    private static void init(){
        FlatDarculaLaf.setup();
    }

    private static void loginForm(){
        login frameLogin = new login();

        frameLogin.setVisible(true);

        frameLogin.enterBtn.addActionListener(e -> {
            exitNotEmpty alert = new exitNotEmpty();
            alert.buttonOK.addActionListener(e1 -> {
                alert.setVisible(false);
            });
            alert.buttonCancel.addActionListener(e1 -> {
                alert.setVisible(false);
            });
            alert.msjLabel.setText("¡Usuario no encontrado!");

            StringBuilder passStringFromArray = new StringBuilder();
            currentUser = bank.validateUser(frameLogin.usernameField.getText(), String.valueOf(passStringFromArray.append(frameLogin.passwordField.getPassword())));

            if(currentUser.getAccount() == null){
                alert.setVisible(true);
                return;
            }
            if (!currentUser.getAccount().equals("")){
                homeForm(frameLogin);
                frameLogin.setVisible(false);
            }
        });
        frameLogin.cancelBtn.addActionListener(e ->{
            System.exit(0);
        });
        frameLogin.cardBtn.addActionListener(e -> {
            JFileChooser chooser = new JFileChooser();
            exitNotEmpty alert = new exitNotEmpty();
            alert.buttonOK.addActionListener(e1 -> {
                alert.setVisible(false);
            });
            alert.buttonCancel.addActionListener(e1 -> {
                alert.setVisible(false);
            });
            alert.msjLabel.setText("¡Tarjeta no aceptada!");

            if (chooser.showOpenDialog(frameLogin) == JFileChooser.APPROVE_OPTION) {
                currentUser.setAccount(bank.readCard(chooser.getSelectedFile().getPath()));
                currentUser = bank.validateUser(currentUser.getAccount());
                if(currentUser.getAccount() == null){
                    alert.setVisible(true);
                    return;
                }
                if (!currentUser.getAccount().equals("")){
                    homeForm(frameLogin);
                    frameLogin.setVisible(false);
                }
            }
            else {
                System.out.println("No Selection ");
            }

        });
        frameLogin.createBtn.addActionListener(e -> {
            exitNotEmpty alert = new exitNotEmpty();
            alert.buttonOK.addActionListener(e2 -> {
                alert.setVisible(false);
            });
            alert.buttonCancel.addActionListener(e2 -> {
                alert.setVisible(false);
            });
            createUser newUserForm = new createUser();
            newUserForm.setVisible(true);

            newUserForm.aceptBtn.addActionListener(e1 -> {

                //Validation username and password not null
                if (newUserForm.usernameField.getText().equals("") || newUserForm.passwordField.getPassword().length == 0){
                    alert.setVisible(true);
                    return;
                }

                user newUser = new user();
                newUser.setUsername(newUserForm.usernameField.getText());
                StringBuilder passStringFromArray = new StringBuilder();
                newUser.setPassword(String.valueOf(passStringFromArray.append(newUserForm.passwordField.getPassword())));
                newUser.setName(newUserForm.nameField.getText());
                newUser.setLast_name(newUserForm.lastnameField.getText());
                newUser.setAge((Integer) newUserForm.ageField.getValue());
                newUser.setPhone(newUserForm.phoneField.getText());
                newUser.setEmail(newUserForm.emailField.getText());
                newUser.setContent(0.0);
                newUser.setAccount(newUser.getUsername()+newUser.getPassword());

                bank.makeUser(newUser);
                currentUser =  newUser;

                JFileChooser chooser = new JFileChooser();
                chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

                if (chooser.showOpenDialog(frameLogin) == JFileChooser.APPROVE_OPTION) {

                    System.out.println(chooser.getSelectedFile().getAbsolutePath());
                    bank.createACard(newUser.getAccount(),chooser.getSelectedFile().getAbsolutePath(), newUser.getUsername()+"CARD");
                }
                else {
                    System.out.println("No save user card");
                }
                newUserForm.dispose();
            });

            newUserForm.cancelBtn.addActionListener(e1 -> {
                newUserForm.dispose();
            });
        });
    }

    private static void homeForm(JFrame parent){
        home frameHome = new home();
        frameHome.setVisible(true);
        frameHome.nameLabel.setText(currentUser.getName());
        frameHome.lastnameLabel.setText(currentUser.getLast_name());
        frameHome.phoneLabel.setText(currentUser.getPhone());
        frameHome.emailLabel.setText(currentUser.getEmail());
        frameHome.ageLabel.setText(""+currentUser.getAge());
        frameHome.usernameLabel.setText(currentUser.getUsername());
        frameHome.contentLabel.setText("$"+currentUser.getContent().toString());

        frameHome.exitBtn.addActionListener(e -> {
            parent.setVisible(true);
            frameHome.dispose();
        });

        frameHome.depositBtn.addActionListener(e -> {
            enterChash depositForm = new enterChash();
            depositForm.setTitle("Depositar");
            depositForm.setVisible(true);
            depositForm.aceptBtn.addActionListener(e1 -> {
                bank.increase(currentUser, Double.parseDouble(depositForm.cashField.getValue()+""));
                frameHome.contentLabel.setText("$"+currentUser.getContent().toString());
                depositForm.dispose();
            });
        });

        frameHome.withdrawalBtn.addActionListener(e -> {
            enterChash withdrawForm = new enterChash();
            exitNotEmpty alertNotChash = new exitNotEmpty();
            alertNotChash.msjLabel.setText("¡No tiene los fondos suficientes!");
            alertNotChash.buttonOK.addActionListener(e2 -> {
                alertNotChash.setVisible(false);
            });
            alertNotChash.buttonCancel.addActionListener(e2 -> {
                alertNotChash.setVisible(false);
            });

            withdrawForm.setTitle("Retirar");
            withdrawForm.setVisible(true);
            withdrawForm.aceptBtn.addActionListener(e1 -> {
                if (bank.decrease(currentUser, Double.parseDouble(withdrawForm.cashField.getValue()+""))){
                    frameHome.contentLabel.setText("$"+currentUser.getContent().toString());
                } else {
                    alertNotChash.setVisible(true);
                }
                withdrawForm.dispose();
            });
        });
    }
}

