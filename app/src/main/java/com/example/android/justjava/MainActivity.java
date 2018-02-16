/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private final int PRICE_PER_CUP = 5;
    /**
     * Variables!
     */
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        String priceMessage = createOrderSummary();
        //String priceMessage = "Total: " + NumberFormat.getCurrencyInstance().format(calculatePrice(this.quantity,5)) + "\nThank You!";
        displayMessage(priceMessage);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method increases the quantity value on the screen
     */
    public void increment(View view) {
        this.quantity++;
        displayQuantity(quantity);
    }

    /**
     * This method decreases the quantity value on the screen
     */
    public void decrement(View view) {
        if (quantity > 1) this.quantity--;
        displayQuantity(quantity);
    }

    /**
     * Calculates the price of the order
     *
     * @param numberOfCoffees number of coffees
     * @return the price
     */
    private int calculatePrice(int numberOfCoffees) {
        return (numberOfCoffees * PRICE_PER_CUP);
    }

    /**
     * Creates a string message from the quantity of coffees and price
     *
     * @return Returns the message to be displayed once the order button is pressed
     */
    private String createOrderSummary() {
        return ("Name: Stephen" +
                "\nQuantity: " + this.quantity +
                "\nTotal: " + NumberFormat.getCurrencyInstance().format(calculatePrice(quantity)) +
                "\nThank You!");
    }
}