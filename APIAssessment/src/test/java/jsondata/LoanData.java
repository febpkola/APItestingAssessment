package jsondata;

public class LoanData {
    public void setBankAccount(BankAccount bankAccount) {
        this.bankAccount = bankAccount;
    }

    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    BankAccount bankAccount;
    String idNumber;
    String name;
    String surname;
}
