package com.example.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class UserDocument {


    @Id
    @GeneratedValue
    private long docId;

    private String docHash;

    private  int userDocumentType;

    private boolean verified;

    public UserDocument(String docHash, int userDocumentType, boolean verified) {
        super();
        this.docHash = docHash;
        this.userDocumentType = userDocumentType;
        this.verified = verified;
    }

    enum UserDocumentType {
        RIDE_PERMIT(1),
        DRIVER_LICENSE(2);

        private int Value;

        public int getValue() {
            return Value;
        }

        UserDocumentType(int value) {
            this.Value = value;
        }

    }

    @ManyToOne(cascade={CascadeType.PERSIST, CascadeType.MERGE, 
        CascadeType.DETACH,  CascadeType.REFRESH})
    @JoinColumn(name = "user_id", referencedColumnName =  "userId")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDocHash() {
        return docHash;
    }

    public void setDocHash(String docHash) {
        this.docHash = docHash;
    }

    public int getUserDocumentType() {
        return userDocumentType;
    }

    public void setUserDocumentType(int userDocumentType) {
        this.userDocumentType = userDocumentType;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }
    
}
