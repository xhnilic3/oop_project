package products;

import java.io.Serializable;

import paymethods.BankTransfer;
import paymethods.CreditCard;
import paymethods.PayType;
import paymethods.Paypal;
import products.databases.ProductDatabase;
import sun.font.CreatedFontTracker;

public abstract class Product implements Serializable {
    private String uuid;
    private boolean isPaid;
    
    private PayType lastPaymentMethod;

    public Product(ProductDatabase db){
        this.uuid = db.generateUuid();
        db.insertNewProduct(this);
    }


    /**
     * Method which process initialization of products. It varies per branch of descendants.
     * @return void
     */
    public abstract void init();


    /**
     * Method that return uuid of given object
     * @return uuid of given object
     */
    public String getUuid(){
        return this.uuid;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    /**
     * Method we use strategy design through (and is used for observer and visitor in the second place)
     * @param type: PayType
     * @return void
     */
    public void setPaid(PayType type) {
        lastPaymentMethod = type;

        if (type instanceof Paypal) System.out.println("Produkt bol zaplateny cez PayPal.");
        else if (type instanceof BankTransfer) System.out.println("Produkt bol zaplateny cez bankovy prevod.");
        else if (type instanceof CreditCard) System.out.println("Produkt bol zaplateny cez kreditnu kartu.");

        type.pay(0);
        setPaid(true);
    }
}