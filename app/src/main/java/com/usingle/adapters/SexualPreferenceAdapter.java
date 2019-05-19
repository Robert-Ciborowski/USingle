/**
 * Class: ContactsAdapter
 * Author: Robert Ciborowski
 * Date: 29/05/2018
 * Description: An adapter that handles the searching of the RecyclerView that shows all contacts.
 */

package com.usingle.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.usingle.R;
import com.usingle.option_providers.SexualPreferenceProvider;
import com.usingle.util.ContextBoundMethod;

public class SexualPreferenceAdapter extends RecyclerView.Adapter<SexualPreferenceAdapter.ViewHolder> {
    // This is the method that should be run when a contact is selected by the user.
    private ContextBoundMethod contextBoundMethod;

    // This is the only constructor.
    public SexualPreferenceAdapter(ContextBoundMethod contextBoundMethod) {
        this.contextBoundMethod = contextBoundMethod;
    }

    // Represents a view for each item in the RecyclerView.
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // These are the views which are used to display the contact.
        private CardView cardView;
        private CheckBox checkBox;

        // This is the contact's position.
        int position;

        // This stores a reference to the list of contacts.
        String sexualPreference;

        // This is the only constructor.
        public ViewHolder(View view, String sexualPreference) {
            super(view);
            cardView = (CardView) view.findViewById(R.id.sexual_preference_list_card_view);
            checkBox = (CheckBox) cardView.findViewById(R.id.sexual_preference_check_box);
            this.sexualPreference = sexualPreference;

            // This sets up an onclick listener for the checkbox.
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // do something
                }
            });
        }

        // This is run when the contact is supposed to show up on screen.
        void bind(int position) {
            this.setPosition(position);

            // Update this to what the checkbox should actually be.
            checkBox.setChecked(false);
        }

        // The following are getters and setters.
        public void setPosition(int position) {
            this.position = position;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    // This creates new views (and is invoked by the layout manager).
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.sexual_preference_list_item, parent, false);
        return new ViewHolder(itemView, "Other");
    }

    // This replaces the contents of a view (and is invoked by the layout manager).
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardView cardView = holder.getCardView();

        // The following sets up views accordingly.
        CheckBox checkBox = (CheckBox) cardView.findViewById(R.id.sexual_preference_check_box);
        checkBox.setText(SexualPreferenceProvider.getSexualPreference(position));
        holder.bind(position);
    }

    // Return the size of the dataset (and is invoked by the layout manager).
    @Override
    public int getItemCount() {
        return SexualPreferenceProvider.getNumberOfSexualPreferences();
    }
}
