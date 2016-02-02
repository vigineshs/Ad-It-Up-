/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import Business.Network.Network;

/**
 *
 * @author Hema
 */
public class WorkRequest {
    private String status, sender, receiver;
    private Network network;
    public enum Type{
        UserAccount("UserAccount WorkRequest"), Bidding("Bidding WorkRequest");
        private String value;
        private Type(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Network getNetwork() {
        return network;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }
    
}
