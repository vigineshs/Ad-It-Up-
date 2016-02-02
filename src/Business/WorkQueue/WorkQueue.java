/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Business.WorkQueue;

import java.util.ArrayList;

/**
 *
 * @author Hema
 */
public class WorkQueue {
    private ArrayList<UserAccountWorkRequest> userAccountWorkRequests;
    private ArrayList<BiddingWorkRequest> biddingWorkRequests, activeBids;
    private boolean flag;

    public WorkQueue() {
        userAccountWorkRequests = new ArrayList<>();
        biddingWorkRequests = new ArrayList<>();
        activeBids = new ArrayList<>();
    }

    public ArrayList<UserAccountWorkRequest> getUserAccountWorkRequests() {
        return userAccountWorkRequests;
    }

    public void setUserAccountWorkRequests(ArrayList<UserAccountWorkRequest> userAccountWorkRequests) {
        this.userAccountWorkRequests = userAccountWorkRequests;
    }

    public ArrayList<BiddingWorkRequest> getBiddingWorkRequests() {
        return biddingWorkRequests;
    }

    public void setBiddingWorkRequests(ArrayList<BiddingWorkRequest> biddingWorkRequests) {
        this.biddingWorkRequests = biddingWorkRequests;
    }

    public ArrayList<BiddingWorkRequest> getActiveBids() {
        return activeBids;
    }

    public void setActiveBids(ArrayList<BiddingWorkRequest> activeBids) {
        this.activeBids = activeBids;
    }
    
    public WorkRequest createAndAddWorkRequest(WorkRequest.Type type) {
        if(type.equals(WorkRequest.Type.Bidding)) {
            WorkRequest biddingWorkRequest = new BiddingWorkRequest();
            return biddingWorkRequest;
        }
        if(type.equals(WorkRequest.Type.UserAccount)) {
            WorkRequest userAccountWorkRequest = new UserAccountWorkRequest();
            userAccountWorkRequests.add((UserAccountWorkRequest) userAccountWorkRequest);
            return userAccountWorkRequest;
        }
        return null;
    }
    
    public void addWorkRequest(BiddingWorkRequest biddingWorkRequest) {
        flag = true;
        for(BiddingWorkRequest biddingWorkRequest1 : biddingWorkRequests) {
            if(biddingWorkRequest.getSender().equals(biddingWorkRequest1.getSender())) {
                if(biddingWorkRequest.getBidCategory().equals(biddingWorkRequest1.getBidCategory())) {
                    if(biddingWorkRequest.getProfileCategory().equals(biddingWorkRequest1.getProfileCategory())) {
                        flag = false;
                        if(biddingWorkRequest.getBidValue() > biddingWorkRequest1.getBidValue()) {
                            biddingWorkRequest1.setBidValue(biddingWorkRequest.getBidValue());
                            biddingWorkRequest1.setAd(biddingWorkRequest.getAd());
                            flag = false;
                        }
                        break;
                    }
                }
                if(!flag)
                    break;
            }
        }
        if(flag) {
            biddingWorkRequests.add(biddingWorkRequest);
        }  
        addActiveBids(biddingWorkRequest);
    }
    
    public void addActiveBids(BiddingWorkRequest biddingWorkRequest) {
        flag = true;
        for(BiddingWorkRequest biddingWorkRequest1 : activeBids) {
            if(biddingWorkRequest.getBidCategory().equals(biddingWorkRequest1.getBidCategory())) {
                if(biddingWorkRequest.getProfileCategory().equals(biddingWorkRequest1.getProfileCategory())) {
                    flag = false;
                    if(biddingWorkRequest.getBidValue() > biddingWorkRequest1.getBidValue()) {
                        biddingWorkRequest1.setBidValue(biddingWorkRequest.getBidValue());
                        biddingWorkRequest1.setAd(biddingWorkRequest.getAd());
                        flag = false;
                    }
                    break;
                }
            }
            if(!flag)
                break;
        }
        if(flag) {
            activeBids.add(biddingWorkRequest);
        }
    }
}
