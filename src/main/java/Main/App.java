/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Main;
import Blockchain.Hashing.Salt;
import UserInterface.*;
import Utils.Utils.*;
import static Utils.Utils.*;
/**
 *
 * @author JUN WEI
 */
public class App {
    public static void main(String[] args) {
        Salt.generate();
        createBlockchainFile();
        createDatabaseDirectory();
        createSecretDirectory();
        new LoginGUI().setVisible(true);
    }
}
