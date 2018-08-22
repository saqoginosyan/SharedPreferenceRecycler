package com.example.sargis.sharedprefrecyclerview.dialogfragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.sargis.sharedprefrecyclerview.utilities.ButtonClickListener;
import com.example.sargis.sharedprefrecyclerview.utilities.PrefUtil;
import com.example.sargis.sharedprefrecyclerview.R;
import com.example.sargis.sharedprefrecyclerview.utilities.Utility;

public class AddDialogFragment extends DialogFragment {
    private EditText inputKey;
    private EditText inputValue;
    public String key;
    public String value;
    private ButtonClickListener buttonClickListener;
    private Button addButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.add_df, container, false);
        addButton = view.findViewById(R.id.button_save);
        inputKey = view.findViewById(R.id.edit_text_key);
        inputValue = view.findViewById(R.id.edit_text_value);
        buttonClickListener = (ButtonClickListener) getActivity();

        enableButton();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                key = Utility.getText(inputKey);
                value = Utility.getText(inputValue);
                PrefUtil.saveString(getActivity(), key, value);
                dismiss();
                buttonClickListener.setButtonClickListener();
            }
        });
        return view;
    }

    private void enableButton() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String keyInput = inputKey.getText().toString().trim();
                addButton.setEnabled(!keyInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };
        inputKey.addTextChangedListener(textWatcher);
    }
}
