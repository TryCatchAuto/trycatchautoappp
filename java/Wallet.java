public class Wallet {
    private String wallet_id;
    private float money;
    private boolean creditCard;
    public Wallet(float money, boolean creditCard) {
        this.money = money;
        this.creditCard = creditCard;
    }

    public boolean isCreditCard() {
        return creditCard;
    }

    public void setCreditCard(boolean creditCard) {
        this.creditCard = creditCard;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getWallet_id() {
        return wallet_id;
    }

    public void setWallet_id(String wallet_id) {
        this.wallet_id = wallet_id;
    }
}
