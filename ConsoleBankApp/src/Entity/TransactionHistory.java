package Entity;

public class TransactionHistory {

    private int transactionID;
    private String transactionName;
    private double amount;
    private String date;
    private String recieverName;
    private Integer recieverAccountNumber;
    private static int transactionIDGenerator=20001;

    public TransactionHistory(String transactionName, double amount, String recieverName, Integer recieverAccountNumber) {
        this.transactionID = transactionIDGenerator++;
        this.transactionName = transactionName;
        this.amount = amount;
        this.date = String.valueOf(java.time.LocalDate.now());
        this.recieverName = recieverName;
        this.recieverAccountNumber = recieverAccountNumber;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionName() {
        return transactionName;
    }

    public void setTransactionName(String transactionName) {
        this.transactionName = transactionName;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRecieverName() {
        return recieverName;
    }

    public void setRecieverName(String recieverName) {
        this.recieverName = recieverName;
    }

    public Integer getRecieverAccountNumber() {
        return recieverAccountNumber;
    }

    public void setRecieverAccountNumber(Integer recieverAccountNumber) {
        this.recieverAccountNumber = recieverAccountNumber;
    }


    @Override
    public String toString() {
        return "TransactionHistory{" +
                "transactionID=" + transactionID +
                ", transactionName='" + transactionName + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", recieverName='" + recieverName + '\'' +
                ", recieverAccountNumber=" + recieverAccountNumber +
                '}';
    }

}
