package com.proj.advoteck;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Pattern;

public class ViewRequestsAdapter extends BaseAdapter {

    private final Context context;
    private final LayoutInflater inflater;
    private final ArrayList<HashMap<String, String>> arrLstData;

    public ViewRequestsAdapter(Context context, ArrayList<HashMap<String, String>> arraylist) {
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.arrLstData = arraylist;
    }

    @Override
    public int getCount() {
        return arrLstData.size();
    }

    @Override
    public Object getItem(int position) {
        return arrLstData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.view_request_items, parent, false);
        }

        HashMap<String, String> itemData = arrLstData.get(position);

        TextView tvUsername = convertView.findViewById(R.id.tvRequestor);
        TextView tvInfo = convertView.findViewById(R.id.tvInformation);
        ImageView ivUploadedImage = convertView.findViewById(R.id.noteImageView);

        tvUsername.setText(itemData.get("username"));
        String infoText = itemData.get("info");
        tvInfo.setText(infoText);

        // Define regex patterns for sensitive information
        Pattern creditCardPattern = Pattern.compile("\\b(?:\\d{4}-){3}\\d{4}\\b"); // e.g., 1234-5678-1234-5678
        Pattern accountNumberPattern = Pattern.compile("\\b\\d{10,14}\\b"); // e.g., 10-14 digit numbers
        Pattern ssnPattern = Pattern.compile("\\b\\d{3}-\\d{2}-\\d{4}\\b"); // e.g., 123-45-6789 (US SSN)

// Check if the text contains sensitive information
        boolean isSensitive = creditCardPattern.matcher(infoText).find() ||
                accountNumberPattern.matcher(infoText).find() ||
                ssnPattern.matcher(infoText).find();

        if (isSensitive) {
            tvInfo.setTextColor(Color.RED);
            tvInfo.append(" ⚠️");
        } else {
            tvInfo.setTextColor(Color.BLACK);
        }

        convertView.setOnClickListener(v -> {
            AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
            dialogBuilder.setTitle("Request Information");

            StringBuilder message = new StringBuilder();
            message.append("Username: ").append(itemData.get("username")).append("\n");
            message.append("Information: ").append(infoText).append("\n");

            if (isSensitive) {
                message.append("⚠️ This information may contain sensitive content.");
            }

            dialogBuilder.setMessage(message.toString());
            dialogBuilder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

            AlertDialog dialog = dialogBuilder.create();
            dialog.show();
        });

        return convertView;
    }
}
