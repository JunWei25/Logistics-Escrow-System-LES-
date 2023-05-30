/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Class;

/**
 *
 * @author JUN WEI
 */
public class Parcel {
    private String packageNo;
    private String packageCreatedDate;
    private String senderName;
    private String senderAddress;
    private String senderContact;
    private String recipientName;
    private String recipientAddress;
    private String recipientContact;
    private String parcelWeight;
    private String deliveryType;
    private double escrowAmount;
    private double serviceFee; 
    private String escrowAgreementStatus;
    private String packageStatus;
    
    public Parcel(String packageNo, String senderName, String recipientName){
        this.packageNo = packageNo;
        this.senderName = senderName;
        this.recipientName = recipientName;
    }
    
    public Parcel(String senderName, String senderAddress, String senderContact, String recipientName, String recipientAddress,String recipientContact, String parcelWeight, String deliveryType, double escrowAmount, double serviceFee, String packageStatus){
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.senderContact = senderContact;
        this.recipientName = recipientName;
        this.recipientAddress = recipientAddress;
        this.recipientContact = recipientContact;
        this.parcelWeight = parcelWeight;
        this.deliveryType = deliveryType;
        this.escrowAmount = escrowAmount;
        this.serviceFee = serviceFee;
        this.packageStatus = packageStatus;
    }

    public String getPackageNo() {
        return packageNo;
    }
   
    public String getSenderName() {
        return senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public String getSenderContact() {
        return senderContact;
    }

    public String getRecipientName() {
        return recipientName;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public String getRecipientContact() {
        return recipientContact;
    }

    public String getDeliveryType() {
        return deliveryType;
    }

    public double getEscrowAmount() {
        return escrowAmount;
    }

    public double getServiceFee() {
        return serviceFee;
    }

    public String getPackageStatus() {
        return packageStatus;
    }

    public String getPackageCreatedDate() {
        return packageCreatedDate;
    }

    public String getParcelWeight() {
        return parcelWeight;
    }
    
    public String getEscrowAgreementStatus(){
        return escrowAgreementStatus;
    }
    
    public String setEscrowAgreementStatus(String escrowAgreementStatus){
        return this.escrowAgreementStatus = escrowAgreementStatus;
    }
    
    
    public void setParcelWeight(String parcelWeight) {
        this.parcelWeight = parcelWeight;
    }
    
    public void setPackageCreatedDate(String packageCreatedDate) {
        this.packageCreatedDate = packageCreatedDate;
    }
    
    public void setPackageNo(String packageNo) {
        this.packageNo = packageNo;
    }
   
    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public void setSenderContact(String senderContact) {
        this.senderContact = senderContact;
    }

    public void setRecipientName(String recipientName) {
        this.recipientName = recipientName;
    }

    public void setRecipientContact(String recipientContact) {
        this.recipientContact = recipientContact;
    }
    
    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public void setDeliveryType(String deliveryType) {
        this.deliveryType = deliveryType;
    }

    public void setEscrowAmount(double escrowAmount) {
        this.escrowAmount = escrowAmount;
    }

    public void setServiceFee(double serviceFee) {
        this.serviceFee = serviceFee;
    }

    public void setPackageStatus(String packageStatus) {
        this.packageStatus = packageStatus;
    }
    
    
    
}

