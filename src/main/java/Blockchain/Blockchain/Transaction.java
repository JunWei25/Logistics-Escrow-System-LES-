/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blockchain.Blockchain;

/**
 *
 * @author JUN WEI
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
public class Transaction implements Serializable{
    private static final long serialVersionUID = 1L;
   
    public String merkleRoot = "0";
    
    public String getMerkleRoot(){
        return merkleRoot;
    }

    public void setMerkleRoot(String merkleRoot) {
        this.merkleRoot = merkleRoot;
    }
    
    
    public void complete(){
        MerkleTree mt = MerkleTree.getInstance(dataLst);
        mt.build();
        this.merkleRoot = mt.getRoot();
    }
    public List<String> dataLst = new ArrayList<>();
//    List<String> tranxLst = Arrays.asList(arr);
//        MerkleTree mt = MerkleTree.getInstance(tranxLst);
//        mt.build();
//        String merkleRoot = mt.getRoot();
//        
//        System.out.println("Merkle Root= "+ merkleRoot);
    public void add(String tranx) {
            dataLst.add(tranx);
    }
    
    @Override
    public String toString() {
        return "Transaction [merkleRoot=" + merkleRoot + ", dataLst=" + dataLst + "]";
    }
    
}
