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
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    private final int PRICE_PER_CUP = 5;
    private int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

/*------------------------------------------------------------------------------------------------*/

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
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
        else {
            Toast toast = Toast.makeText(this, "Minimum order of 1", Toast.LENGTH_LONG);
            toast.show();
        }
        displayQuantity(quantity);
    }

/*------------------------------------------------------------------------------------------------*/

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * Calculates the price of the order
     *
     * @param numberOfCoffees number of coffees
     * @return the total price
     */
    private int calculatePrice(int numberOfCoffees) {
        return (numberOfCoffees * PRICE_PER_CUP);
    }

    /**
     * Creates the string for the order summary
     *
     * @return Returns the message to be displayed once the order button is pressed
     */
    private String createOrderSummary() {
        CheckBox whippedCreamCheckBox = findViewById(R.id.checkbox_whipped_cream);

        String returnString = "Name: Stephen" +
                "\nQuantity: " + this.quantity;
        if (whippedCreamCheckBox.isChecked()) returnString += "\nwith Whipped Cream";
        returnString += "\nTotal: " +
                NumberFormat.getCurrencyInstance().format(calculatePrice(quantity)) +
                "\nThank You!";

        return returnString;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary());
    }
}