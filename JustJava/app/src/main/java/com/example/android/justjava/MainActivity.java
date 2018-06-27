/**
 * IMPORTANT: Make sure you are using the correct package name. 
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match teh package name found
 * in the project's AndroidManifest.xml file.
 **/

package com.example.android.justjava;



import android.content.Intent;
import android.net.Uri;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    int quantity = 0;
    boolean whippedChecked = false;
    boolean chocolateChecked = false;
    int cost = 0;
    String name = "";

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText nameEditTextView = (EditText) findViewById(R.id.name_edit_text_view);
        name = nameEditTextView.getText().toString();
        createOrderSummary();
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display() {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + quantity);
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

    private void calculatePrice() {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.whipped_cream_checkBox);
        whippedChecked = whippedCreamCheckBox.isChecked();
        CheckBox chocolateCheckbox = (CheckBox) findViewById(R.id.chocolate_checkBox);
        chocolateChecked = chocolateCheckbox.isChecked();
        int price = 10;
        if(whippedChecked) price++;
        if(chocolateChecked) price += 2;
        cost = quantity*price;
    }

    public void createOrderSummary() {
        calculatePrice();
        String summary = "Quantity: " + quantity;
        summary += "\nAdd whipped cream? " + whippedChecked;
        summary += "\nAdd chocolate? " + chocolateChecked;
        summary += "\nPrice: $" + cost;
        summary += "\nThank You!";
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Coffee order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, summary);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }


    /**
     * This method increases the quantity by one.
     */
    public void incQuantity(View view) {
        if (quantity==100)Toast.makeText(getApplicationContext(), "Woah! Thats too much caffeine!", Toast.LENGTH_SHORT).show();
        quantity++;
        display();
    }

    /**
     * This method decreases the quantity by one.
     */
    public void decQuantity(View view) {
        if(quantity!=0){
            quantity--;
            display();
        }
    }

    public void checkTotal(View view) {
        calculatePrice();
        Toast.makeText(this, "$" + cost, Toast.LENGTH_SHORT).show();
    }
    /**
     * This method displays the given text on the screen.
     */
//    private void displayMessage(String message) {
//
//        orderSummaryTextView.setText(message + "\n" + "Thank You!");
//    }
}