/*
 */
package se.stromqvist.david.skolarbeten.iv1350.seminarie3pos.util;

/**
 *
 * @author David
 * 
 * Used to specify the quantity and kind of amount of an item.
 * Instances are immutable.
 */
public class Amount {
	
    private final AmountENUM type;
    private final double amount;

    /**
     * Creates an amount object with a value representing the amount
     * and a type enum representing the kind of amount it is. 
     * @param amount
     * @param type
     */
    public Amount(double amount, AmountENUM type)
    {
        this.amount = amount;
        this.type = type;
    }

    /**
     * @return the type
     */
    public AmountENUM getType() {
        return type;
    }


    /**
     * @return the amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * @param amount the quantity the new Amount will have.
     * 
     * @return A new Amount object with the same type as the current one
     * and with the new quantity as amount.
     * 
     */
    public Amount setAmount(double amount) {
        return new Amount (amount, type);
    }

    /**
     * Used to increase the current amount with the amount value from an 
     * Amount object. Only works if the type is the same 
     * otherwise null is returned
     * 
     * @param amountToAdd adds the quantity from amountToAdd to this amount.
     * 
     * @return A new Amount object with the same type as the current one
     * with the new quantity as amount.
     */
    public Amount addAmount(Amount amountToAdd)
    {
        Amount newAmount = null;
        if (amountToAdd.getType() == type)
            newAmount = new Amount( (amount + amountToAdd.getAmount()), type);
        return newAmount;
    }

    /**
     * Used to increase the current amount with a new quantity in the form of a double.
     * 
     * @param amount adds the quantity from amount to this amount.
     * 
     * @return A new Amount object with the same type as the current one
     * with the new quantity + old quantity as amount.
     */
    public Amount addAmount(double amount)
    {
        return new Amount( (this.amount + amount), type);
    }
    
    @Override
    public boolean equals(Object other)
    {
        if (!(other instanceof Amount))
        {
            return false;
        }
        Amount otherAmount = (Amount) other;
        
        if (type != otherAmount.type)
        {
            return false;
        }
        
        return amount == otherAmount.amount; 
    }


}
