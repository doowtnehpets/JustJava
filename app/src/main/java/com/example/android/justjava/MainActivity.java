/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
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
        if (quantity < 100) this.quantity++;
        else {
            Toast.makeText(this, "Maximum order of 100", Toast.LENGTH_LONG).show();
            return;
        }
        displayQuantity(quantity);
    }

    /**
     * This method decreases the quantity value on the screen
     */
    public void decrement(View view) {
        if (quantity > 1) this.quantity--;
        else {
            Toast.makeText(this, "Minimum order of 1", Toast.LENGTH_LONG).show();
            return;
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
    private int calculatePrice(int numberOfCoffees, boolean addWhippedCream, boolean addChocolate) {
        int totalPrice = numberOfCoffees * PRICE_PER_CUP;
        if (addWhippedCream) totalPrice += numberOfCoffees;
        if (addChocolate) totalPrice += numberOfCoffees * 2;

        return totalPrice;
    }

    /**
     * Creates the string for the order summary
     *
     * @return Returns the message to be displayed once the order button is pressed
     */
    private String createOrderSummary() {
        EditText name = findViewById(R.id.edittext_name);
        CheckBox whippedCreamCheckBox = findViewById(R.id.checkbox_whipped_cream);
        CheckBox chocolate = findViewById(R.id.checkbox_chocolate);

        //Start creating the returnString and add name and quantity
        String returnString = "Name: " + name.getText().toString() +
                "\nQuantity: " + this.quantity;

        //If whipped cream checkbox is checked then add the line add whipped cream?
        if (whippedCreamCheckBox.isChecked())
            returnString += "\nadd whipped Cream? " + whippedCreamCheckBox.isChecked();

        //If chocolate checkbox is checked then add the line add chocolate?
        if (chocolate.isChecked()) returnString += "\nadd chocolate? " + chocolate.isChecked();

        //Add total price to string
        returnString += "\nTotal: " +
                NumberFormat.getCurrencyInstance().format(calculatePrice(quantity, whippedCreamCheckBox.isChecked(), chocolate.isChecked())) +
                "\nThank You!";

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "JustJava order for " + name.getText().toString());
        intent.putExtra(Intent.EXTRA_TEXT, returnString);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }

        return returnString;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary());
    }
}