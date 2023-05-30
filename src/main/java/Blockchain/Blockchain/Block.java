/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Blockchain.Blockchain;

/**
 *
 * @author JUN WEI
 */
import Blockchain.Blockchain.Transaction;
import Blockchain.Hashing.Hasher;
import java.io.Serializable;

public class Block implements Serializable{
    
    private static final long serialVersionUID = 1;
    
    public Header blockHeader;
    public Header getBlockHeader() {
        return blockHeader;
    }
    /* composition relationship */
    public Block(String previousHash, String merkleRoot, int currentBlockIndex) {
        super();
        long now = System.currentTimeMillis();
        /* construct part object upon object construction */
        this.blockHeader = new Header();
        this.blockHeader.setPreviousHash(previousHash);
        this.blockHeader.setTimestamp(now);
        //hashing with sha256 - the input is joined with previousHash+now+merkleRoot
        String currentHash = Hasher.sha256( 
                String.join("+", previousHash, String.valueOf(now), merkleRoot) );
        this.blockHeader.setCurrentHash(currentHash);
        this.blockHeader.setIndex(currentBlockIndex);
    }
    /* composition relationship - inner class definition for part object*/
    public class Header implements Serializable{
        private static final long serialVersionUID = 1L;
        //data member
        public int index;
        public String currentHash, previousHash;
        public long timestamp;
        public String merkleRoot;
        
        @Override
        public String toString() {
            return "Header [index=" + index + ", currentHash=" + currentHash + ", previousHash=" + previousHash
                    + ", timestamp=" + timestamp + "]";
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }
        
        //getset methods
        public String getCurrentHash() {
            return currentHash;
        }
        public void setCurrentHash(String currentHash) {
            this.currentHash = currentHash;
        }
        public String getPreviousHash() {
            return previousHash;
        }
        public void setPreviousHash(String previousHash) {
            this.previousHash = previousHash;
        }
        public long getTimestamp() {
            return timestamp;
        }
        public void setTimestamp(long timestamp) {
            this.timestamp = timestamp;
        }

        public String getMerkleRoot() {
            return merkleRoot;
        }

        public void setMerkleRoot(String merkleRoot) {
            this.merkleRoot = merkleRoot;
        }
        
        
    }
    
    /* aggregation relationship */
    public Transaction tranxLst;
    public void setTranxLst(Transaction tranxLst) {
        this.tranxLst = tranxLst;
    }

    public Transaction getTranxLst() {
        return tranxLst;
    }
    
    
    @Override
    public String toString() {
        return "Block [blockHeader=" + blockHeader + ", tranxLst=" + tranxLst + "]";
    }
    
    
}
