package org.epamcampus.encapsulation;

// Bad -> Anemic class. This class has public fields, which can be accessed and modified directly from outside the class, violating encapsulation principles.
public class BadBankAccount {
    public String accountID;
    public double balance; // Anyone can directly modify the balance without any control or validation, which can lead to inconsistent states.
    public String ownerId;
}
