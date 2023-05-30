/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author JUN WEI
 */
public class EscrowAgreement {
    private String packageNo;
    private String senderName;
    private String senderAddress;
    private String senderAgreementStatus;
    private String recipientName;
    private String recipientAddress;
    private String recipientAgreementStatus;
    private String witness;
    private String witnessAgreementStatus;
    private String escrowAgreementDate;
    private String escrowFee;

    public String getPackageNo() {
        return packageNo;
    }

    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getSenderAgreementStatus() {
        return senderAgreementStatus;
    }

    public void setSenderAgreementStatus(String senderAgreementStatus) {
        this.senderAgreementStatus = senderAgreementStatus;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getRecipientAgreementStatus() {
        return recipientAgreementStatus;
    }

    public void setRecipientAgreementStatus(String recipientAgreementStatus) {
        this.recipientAgreementStatus = recipientAgreementStatus;
    }

    public String getWitness() {
        return witness;
    }

    public void setWitness(String witness) {
        this.witness = witness;
    }

    public String getWitnessAgreementStatus() {
        return witnessAgreementStatus;
    }

    public void setWitnessAgreementStatus(String witnessAgreementStatus) {
        this.witnessAgreementStatus = witnessAgreementStatus;
    }

    public String getEscrowAgreementDate() {
        return escrowAgreementDate;
    }

    public void setEscrowAgreementDate(String escrowAgreementDate) {
        this.escrowAgreementDate = escrowAgreementDate;
    }

    public String getEscrowFee() {
        return escrowFee;
    }

    public void setEscrowFee(String escrowFee) {
        this.escrowFee = escrowFee;
    }
    
    @Override
    public String toString() {
        return "\n Escrow Agreement Details\n"+
                "Package No: "+ packageNo+"\n"+
                "Sender Name: "+senderName+"\n"+
                "Sender Address: "+senderAddress+"\n"+
                "Sender Agreement Status: "+senderAgreementStatus;
    }
}
