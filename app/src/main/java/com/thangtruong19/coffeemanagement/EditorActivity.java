package com.thangtruong19.coffeemanagement;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.thangtruong19.coffeemanagement.data.OrderContract;

public class EditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    /** Identifier for the order data loader */
    private static final int EXISTING_ORDER_LOADER = 0;

    /** Content URI for the existing order (null if it's a new order) */
    private Uri mCurrentOrderUri;

    /** EditText field to enter the coffee's name */
    private EditText mCoffeeNameEditText;

    /** EditText field to enter the tea's name */
    private EditText mTeaNameEditText;

    /** EditText field to enter the milk's name */
    private EditText mMilkNameEditText;

    /** EditText field to enter the coffee's number */
    private EditText mCoffeeNumberEditText;

    /** EditText field to enter the tea's number */
    private EditText mTeaNumberEditText;

    /** EditText field to enter the milk's number */
    private EditText mMilkNumberEditText;

    /** Spinner field to enter the table */
    private Spinner mTableSpinner;

    /** EditText field to display the total money */
    private EditText mTotalMoneyEditText;

    /**
     * Table to choose. The possible valid values are in the OrderContract.java file:
     *
     */
    private int mTable = OrderContract.OrderEntry.TABLE_ONE;

    /** Boolean flag that keeps track of whether the pet has been edited (true) or not (false) */
    private boolean mPetHasChanged = false;

    /**
     * OnTouchListener that listens for any user touches on a View, implying that they are modifying
     * the view, and we change the mPetHasChanged boolean to true.
     */
    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mPetHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        // Examine the intent that was used to launch this activity,
        // in order to figure out if we're creating a new pet or editing an existing one.
        Intent intent = getIntent();
        mCurrentOrderUri = intent.getData();

        // If the intent DOES NOT contain a pet content URI, then we know that we are
        // creating a new pet.
        if (mCurrentOrderUri == null) {
            // This is a new pet, so change the app bar to say "Add a Pet"
            setTitle("Add a new order");

            // Invalidate the options menu, so the "Delete" menu option can be hidden.
            // (It doesn't make sense to delete a pet that hasn't been created yet.)
            invalidateOptionsMenu();
        } else {
            // Otherwise this is an existing pet, so change app bar to say "Edit Pet"
            setTitle("Thanh toán");

            // Initialize a loader to read the pet data from the database
            // and display the current values in the editor
            getLoaderManager().initLoader(EXISTING_ORDER_LOADER, null, this);
        }

        // Find all relevant views that we will need to read user input from
        mCoffeeNameEditText = (EditText) findViewById(R.id.edit_coffee_name);
        mTeaNameEditText = (EditText) findViewById(R.id.edit_tea_name);
        mMilkNameEditText = (EditText) findViewById(R.id.edit_milk_name);

        mCoffeeNumberEditText = (EditText) findViewById(R.id.edit_coffee_num);
        mTeaNumberEditText = (EditText) findViewById(R.id.edit_tea_num);
        mMilkNumberEditText = (EditText) findViewById(R.id.edit_milk_num);

        mTableSpinner = (Spinner) findViewById(R.id.spinner_table_order);

        mTotalMoneyEditText = (EditText) findViewById(R.id.edit_total_money);

        // Setup OnTouchListeners on all the input fields, so we can determine if the user
        // has touched or modified them. This will let us know if there are unsaved changes
        // or not, if the user tries to leave the editor without saving.
        mCoffeeNameEditText.setOnTouchListener(mTouchListener);
        mTeaNameEditText.setOnTouchListener(mTouchListener);
        mMilkNameEditText.setOnTouchListener(mTouchListener);

        mCoffeeNumberEditText.setOnTouchListener(mTouchListener);
        mTeaNumberEditText.setOnTouchListener(mTouchListener);
        mMilkNumberEditText.setOnTouchListener(mTouchListener);

        mTableSpinner.setOnTouchListener(mTouchListener);

        mTotalMoneyEditText.setOnTouchListener(mTouchListener);

        setupSpinner();
    }

    /**
     * Setup the dropdown spinner that allows the user to select the table.
     */
    private void setupSpinner() {
        // Create adapter for spinner. The list options are from the String array it will use
        // the spinner will use the default layout
        ArrayAdapter genderSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.array_table_options, android.R.layout.simple_spinner_item);

        // Specify dropdown layout style - simple list view with 1 item per line
        genderSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        // Apply the adapter to the spinner
        mTableSpinner.setAdapter(genderSpinnerAdapter);

        // Set the integer mSelected to the constant values
        mTableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals("Bàn 1")) {
                        mTable = OrderContract.OrderEntry.TABLE_ONE;
                    } else if (selection.equals("Bàn 2")) {
                        mTable = OrderContract.OrderEntry.TABLE_TWO;
                    } else if (selection.equals("Bàn 3")) {
                        mTable = OrderContract.OrderEntry.TABLE_THREE;
                    } else if (selection.equals("Bàn 4")) {
                        mTable = OrderContract.OrderEntry.TABLE_FOUR;
                    } else if (selection.equals("Bàn 5")) {
                        mTable = OrderContract.OrderEntry.TABLE_FIVE;
                    } else if (selection.equals("Bàn 6")) {
                        mTable = OrderContract.OrderEntry.TABLE_SIX;
                    } else if (selection.equals("Bàn 7")) {
                        mTable = OrderContract.OrderEntry.TABLE_SEVEN;
                    } else if (selection.equals("Bàn 8")) {
                        mTable = OrderContract.OrderEntry.TABLE_EIGHT;
                    } else if (selection.equals("Bàn 9")) {
                        mTable = OrderContract.OrderEntry.TABLE_NINE;
                    } else if (selection.equals("Bàn 10")) {
                        mTable = OrderContract.OrderEntry.TABLE_TEN;
                    } else if (selection.equals("Bàn 11")) {
                        mTable = OrderContract.OrderEntry.TABLE_ELEVEN;
                    } else if (selection.equals("Bàn 12")) {
                        mTable = OrderContract.OrderEntry.TABLE_TWELVE;
                    } else if (selection.equals("Bàn 13")) {
                        mTable = OrderContract.OrderEntry.TABLE_THIRTTEEN;
                    } else if (selection.equals("Bàn 14")) {
                        mTable = OrderContract.OrderEntry.TABLE_FOURTEEN;
                    } else if (selection.equals("Bàn 15")) {
                        mTable = OrderContract.OrderEntry.TABLE_FIFTHTEEN;
                    } else if (selection.equals("Bàn 16")) {
                        mTable = OrderContract.OrderEntry.TABLE_SIXTEEN;
                    } else if (selection.equals("Bàn 17")) {
                        mTable = OrderContract.OrderEntry.TABLE_SEVENTEEN;
                    } else if (selection.equals("Bàn 18")) {
                        mTable = OrderContract.OrderEntry.TABLE_EIGHTTEEN;
                    } else if (selection.equals("Bàn 19")) {
                        mTable = OrderContract.OrderEntry.TABLE_NINETEEN;
                    } else if (selection.equals("Bàn 20")) {
                        mTable = OrderContract.OrderEntry.TABLE_TWENTY;
                    } else if (selection.equals("Bàn 21")) {
                        mTable = OrderContract.OrderEntry.TABLE_TWENTY_ONE;
                    } else if (selection.equals("Bàn 22")) {
                        mTable = OrderContract.OrderEntry.TABLE_TWENTY_TWO;
                    } else if (selection.equals("Bàn 23")) {
                        mTable = OrderContract.OrderEntry.TABLE_TWENTY_THREE;
                    } else if (selection.equals("Bàn 24")) {
                        mTable = OrderContract.OrderEntry.TABLE_TWENTY_FOUR;
                    }
                }
            }

            // Because AdapterView is an abstract class, onNothingSelected must be defined
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mTable = OrderContract.OrderEntry.TABLE_ONE;
            }
        });
    }

    /**
     * Get user input from editor and save pet into database.
     */
    private void savePet() {
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String coffeeNameString = mCoffeeNameEditText.getText().toString().trim();
        String teaNameString = mTeaNameEditText.getText().toString().trim();
        String milkNameString = mMilkNameEditText.getText().toString().trim();

        String coffeeNumberString = mCoffeeNumberEditText.getText().toString().trim();
        String teaNumberString = mTeaNumberEditText.getText().toString().trim();
        String milkNumberString = mMilkNumberEditText.getText().toString().trim();

        int coffeeNumber,teaNumber,milkNumber;
        coffeeNumber=0;
        teaNumber=0;
        milkNumber=0;

        if (!TextUtils.isEmpty(coffeeNumberString)) {
            coffeeNumber = Integer.parseInt(coffeeNumberString);
        }
        if (!TextUtils.isEmpty(teaNumberString)) {
            teaNumber = Integer.parseInt(teaNumberString);
        }
        if (!TextUtils.isEmpty(milkNumberString)) {
            milkNumber = Integer.parseInt(milkNumberString);
        }
        int totalMoney=25000*coffeeNumber+20000*teaNumber+30000*milkNumber;
        mTotalMoneyEditText.setText(""+totalMoney);
        // Check if this is supposed to be a new order
        // and check if all the fields in the editor are blank
        if (mCurrentOrderUri == null &&
                TextUtils.isEmpty(coffeeNameString) &&TextUtils.isEmpty(teaNameString) &&TextUtils.isEmpty(milkNameString)
                && TextUtils.isEmpty(coffeeNumberString) &&TextUtils.isEmpty(teaNumberString)&&TextUtils.isEmpty(milkNumberString)
                ) {
            // Since no fields were modified, we can return early without creating a new pet.
            // No need to create ContentValues and no need to do any ContentProvider operations.
            return;
        }

        // Create a ContentValues object where column names are the keys,
        // and pet attributes from the editor are the values.
        ContentValues values = new ContentValues();
        values.put(OrderContract.OrderEntry.COLUMN_COFFEE_NAME, coffeeNameString);
        values.put(OrderContract.OrderEntry.COLUMN_TEA_NAME, teaNameString);
        values.put(OrderContract.OrderEntry.COLUMN_MILK_NAME, milkNameString);

        values.put(OrderContract.OrderEntry.COLUMN_COFFEE_NUMBER, coffeeNumber);
        values.put(OrderContract.OrderEntry.COLUMN_TEA_NUMBER, teaNumber);
        values.put(OrderContract.OrderEntry.COLUMN_MILK_NUMBER, milkNumber);

        values.put(OrderContract.OrderEntry.COLUMN_TABLE_ORDER, mTable);


        // If the weight is not provided by the user, don't try to parse the string into an
        // integer value. Use 0 by default.

        values.put(OrderContract.OrderEntry.COLUMN_TOTAL_MONEY, totalMoney);

        // Determine if this is a new or existing order by checking if mCurrentOrderUri is null or not
        if (mCurrentOrderUri == null) {
            // This is a NEW order, so insert a new order into the provider,
            // returning the content URI for the new pet.
            Uri newUri = getContentResolver().insert(OrderContract.OrderEntry.CONTENT_URI, values);

            // Show a toast message depending on whether or not the insertion was successful.
            if (newUri == null) {
                // If the new content URI is null, then there was an error with insertion.
                Toast.makeText(this, "Insertion failed",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast.
                Toast.makeText(this, "Insertion success",
                        Toast.LENGTH_SHORT).show();
            }
        } else {
            // Otherwise this is an EXISTING order, so update the order with content URI: mCurrentOrderUri
            // and pass in the new ContentValues. Pass in null for the selection and selection args
            // because mCurrentOrderUri will already identify the correct row in the database that
            // we want to modify.
            int rowsAffected = getContentResolver().update(mCurrentOrderUri, values, null, null);

            // Show a toast message depending on whether or not the update was successful.
            if (rowsAffected == 0) {
                // If no rows were affected, then there was an error with the update.
                Toast.makeText(this, "Update failed",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the update was successful and we can display a toast.
                Toast.makeText(this, "Update succesful",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu options from the res/menu/menu_editor.xml file.
        // This adds menu items to the app bar.
        getMenuInflater().inflate(R.menu.menu_editor, menu);
        return true;
    }

    /**
     * This method is called after invalidateOptionsMenu(), so that the
     * menu can be updated (some menu items can be hidden or made visible).
     */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        // If this is a new pet, hide the "Delete" menu item.
        if (mCurrentOrderUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_thanh_toan);
            menuItem.setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // User clicked on a menu option in the app bar overflow menu
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Save pet to database
                savePet();
                // Exit activity
                finish();
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_thanh_toan:
                // Pop up confirmation dialog for deletion
                showPaymentConfirmationDialog();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // If the pet hasn't changed, continue with navigating up to parent activity
                // which is the {@link CatalogActivity}.
                if (!mPetHasChanged) {
                    NavUtils.navigateUpFromSameTask(EditorActivity.this);
                    return true;
                }

                // Otherwise if there are unsaved changes, setup a dialog to warn the user.
                // Create a click listener to handle the user confirming that
                // changes should be discarded.
                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                                NavUtils.navigateUpFromSameTask(EditorActivity.this);
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * This method is called when the back button is pressed.
     */
    @Override
    public void onBackPressed() {
        // If the pet hasn't changed, continue with handling back button press
        if (!mPetHasChanged) {
            super.onBackPressed();
            return;
        }

        // Otherwise if there are unsaved changes, setup a dialog to warn the user.
        // Create a click listener to handle the user confirming that changes should be discarded.
        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        // Since the editor shows all pet attributes, define a projection that contains
        // all columns from the pet table
        String[] projection = {
                OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_COFFEE_NAME,
                OrderContract.OrderEntry.COLUMN_TEA_NAME,
                OrderContract.OrderEntry.COLUMN_MILK_NAME,
                OrderContract.OrderEntry.COLUMN_COFFEE_NUMBER,
                OrderContract.OrderEntry.COLUMN_TEA_NUMBER,
                OrderContract.OrderEntry.COLUMN_MILK_NUMBER,
                OrderContract.OrderEntry.COLUMN_TABLE_ORDER,
                OrderContract.OrderEntry.COLUMN_TOTAL_MONEY};

        // This loader will execute the ContentProvider's query method on a background thread
        return new CursorLoader(this,   // Parent activity context
                mCurrentOrderUri,         // Query the content URI for the current order
                projection,             // Columns to include in the resulting Cursor
                null,                   // No selection clause
                null,                   // No selection arguments
                null);                  // Default sort order
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        // Bail early if the cursor is null or there is less than 1 row in the cursor
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }

        // Proceed with moving to the first row of the cursor and reading data from it
        // (This should be the only row in the cursor)
        if (cursor.moveToFirst()) {
            // Find the columns of order attributes that we're interested in
            int coffeeNameColumnIndex = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_COFFEE_NAME);
            int teaNameColumnIndex = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_TEA_NAME);
            int milkNameColumnIndex = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_MILK_NAME);
            int coffeeNumberColumnIndex = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_COFFEE_NUMBER);
            int teaNumberColumnIndex = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_TEA_NUMBER);
            int milkNumberColumnIndex = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_MILK_NUMBER);
            int tableColumnIndex = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_TABLE_ORDER);
            int totalMoneyColumnIndex = cursor.getColumnIndex(OrderContract.OrderEntry.COLUMN_TOTAL_MONEY);

            // Extract out the value from the Cursor for the given column index
            String coffeeName = cursor.getString(coffeeNameColumnIndex);
            String teaName = cursor.getString(teaNameColumnIndex);
            String milkName = cursor.getString(milkNameColumnIndex);
            int coffeeNumber = cursor.getInt(coffeeNumberColumnIndex);
            int teaNumber = cursor.getInt(teaNumberColumnIndex);
            int milkNumber = cursor.getInt(milkNumberColumnIndex);
            int table = cursor.getInt(tableColumnIndex);
            int totalMoney = cursor.getInt(totalMoneyColumnIndex);

            // Update the views on the screen with the values from the database
            mCoffeeNameEditText.setText(coffeeName);
            mTeaNameEditText.setText(teaName);
            mMilkNameEditText.setText(milkName);
            mCoffeeNumberEditText.setText(Integer.toString(coffeeNumber));
            mTeaNumberEditText.setText(Integer.toString(teaNumber));
            mMilkNumberEditText.setText(Integer.toString(milkNumber));
            mTotalMoneyEditText.setText(Integer.toString(totalMoney));
            // Table is a dropdown spinner, so map the constant value from the database
            // into one of the dropdown options (0 is Unknown, 1 is Male, 2 is Female).
            // Then call setSelection() so that option is displayed on screen as the current selection.
            switch (table) {
                case OrderContract.OrderEntry.TABLE_ONE:
                    mTableSpinner.setSelection(0);
                    break;
                case OrderContract.OrderEntry.TABLE_TWO:
                    mTableSpinner.setSelection(1);
                    break;
                case OrderContract.OrderEntry.TABLE_THREE:
                    mTableSpinner.setSelection(2);
                    break;
                case OrderContract.OrderEntry.TABLE_FOUR:
                    mTableSpinner.setSelection(3);
                    break;
                case OrderContract.OrderEntry.TABLE_FIVE:
                    mTableSpinner.setSelection(4);
                    break;
                case OrderContract.OrderEntry.TABLE_SIX:
                    mTableSpinner.setSelection(5);
                    break;
                case OrderContract.OrderEntry.TABLE_SEVEN:
                    mTableSpinner.setSelection(6);
                    break;
                case OrderContract.OrderEntry.TABLE_EIGHT:
                    mTableSpinner.setSelection(7);
                    break;
                case OrderContract.OrderEntry.TABLE_NINE:
                    mTableSpinner.setSelection(8);
                    break;
                case OrderContract.OrderEntry.TABLE_TEN:
                    mTableSpinner.setSelection(9);
                    break;
                case OrderContract.OrderEntry.TABLE_ELEVEN:
                    mTableSpinner.setSelection(10);
                    break;
                case OrderContract.OrderEntry.TABLE_TWELVE:
                    mTableSpinner.setSelection(11);
                    break;
                case OrderContract.OrderEntry.TABLE_THIRTTEEN:
                    mTableSpinner.setSelection(12);
                    break;
                case OrderContract.OrderEntry.TABLE_FOURTEEN:
                    mTableSpinner.setSelection(13);
                    break;
                case OrderContract.OrderEntry.TABLE_FIFTHTEEN:
                    mTableSpinner.setSelection(14);
                    break;
                case OrderContract.OrderEntry.TABLE_SIXTEEN:
                    mTableSpinner.setSelection(15);
                    break;
                case OrderContract.OrderEntry.TABLE_SEVENTEEN:
                    mTableSpinner.setSelection(16);
                    break;
                case OrderContract.OrderEntry.TABLE_EIGHTTEEN:
                    mTableSpinner.setSelection(17);
                    break;
                case OrderContract.OrderEntry.TABLE_NINETEEN:
                    mTableSpinner.setSelection(18);
                    break;
                case OrderContract.OrderEntry.TABLE_TWENTY:
                    mTableSpinner.setSelection(19);
                    break;
                case OrderContract.OrderEntry.TABLE_TWENTY_ONE:
                    mTableSpinner.setSelection(20);
                    break;
                case OrderContract.OrderEntry.TABLE_TWENTY_TWO:
                    mTableSpinner.setSelection(21);
                    break;
                case OrderContract.OrderEntry.TABLE_TWENTY_THREE:
                    mTableSpinner.setSelection(22);
                    break;
                case OrderContract.OrderEntry.TABLE_TWENTY_FOUR:
                    mTableSpinner.setSelection(23);
                    break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        // If the loader is invalidated, clear out all the data from the input fields.
        mCoffeeNameEditText.setText("");
        mTeaNameEditText.setText("");
        mMilkNameEditText.setText("");
        mCoffeeNumberEditText.setText("");
        mTeaNumberEditText.setText("");
        mMilkNumberEditText.setText("");
        mTotalMoneyEditText.setText("");
        mTableSpinner.setSelection(0); // Select "Unknown" gender
    }

    /**
     * Show a dialog that warns the user there are unsaved changes that will be lost
     * if they continue leaving the editor.
     *
     * @param discardButtonClickListener is the click listener for what to do when
     *                                   the user confirms they want to discard their changes
     */
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Có sự thay đổi trong order bạn có muốn thoát không");
        builder.setPositiveButton("Thoát", discardButtonClickListener);
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Keep editing" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Prompt the user to confirm that they want to pay this table.
     */
    private void showPaymentConfirmationDialog() {
        // Create an AlertDialog.Builder and set the message, and click listeners
        // for the postivie and negative buttons on the dialog.
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Thanh toán bàn này?");
        builder.setPositiveButton("Thanh toán", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Delete" button, so delete the pet.
                deletePet();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User clicked the "Cancel" button, so dismiss the dialog
                // and continue editing the pet.
                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Perform the deletion of the pet in the database.
     */
    private void deletePet() {
        // Only perform the delete if this is an existing order.
        if (mCurrentOrderUri != null) {
            // Call the ContentResolver to delete the order at the given content URI.
            // Pass in null for the selection and selection args because the mCurrentPetUri
            // content URI already identifies the order that we want.
            int rowsDeleted = getContentResolver().delete(mCurrentOrderUri, null, null);

            // Show a toast message depending on whether or not the delete was successful.
            if (rowsDeleted == 0) {
                // If no rows were deleted, then there was an error with the delete.
                Toast.makeText(this, "Thamh toán không thành công",
                        Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the delete was successful and we can display a toast.
                Toast.makeText(this, "Thanh toán thành công",
                        Toast.LENGTH_SHORT).show();
            }
        }

        // Close the activity
        finish();
    }
}
