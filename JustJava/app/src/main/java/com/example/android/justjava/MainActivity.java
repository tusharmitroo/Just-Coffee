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

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;
    boolean whippedChecked = false;

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        whippedChecked = whippedCreamCheckBox.isChecked();
        createOrderSummary(calculatePrice());
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /*
    /**
     * This method displays the given price on the screen.
     *
    private void displayPrice(int number) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(number));
    }
    */

    private int calculatePrice() {
        return quantity*10;
    }

    public void createOrderSummary(int price) {
        String summary = "Quantity: " + quantity + "\nAdd whipped cream? " + whippedChecked + "\nPrice: $" + price;
        displayMessage(summary);
    }


    /**
     * This method increases the quantity by one.
     */
    public void incQuantity(View view) {
        display(++quantity);
    }

    /**
     * This method decreases the quantity by one.
     */
    public void decQuantity(View view) {
        if(quantity!=0) display(--quantity);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message + "\n" + "Thank You!");
    }
}