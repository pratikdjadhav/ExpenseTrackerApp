public class Transaction {

    private int id;
    private String date;
    private String type;
    private double amount;
    private String category;
    private String note;

    public Transaction(int id, String date, String type, double amount, String category, String note) {

        this.id = id;
        this.date = date;
        this.type = type;
        this.amount = amount;
        this.category = category;
        this.note = note;

    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getAmount() {
        return this.amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getNote() {
        return this.note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return id + "| " + date + " | " + type + " |" + amount + " | " + category + " | " + note;
    }

}