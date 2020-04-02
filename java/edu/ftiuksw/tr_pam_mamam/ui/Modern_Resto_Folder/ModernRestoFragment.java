package edu.ftiuksw.tr_pam_mamam.ui.Modern_Resto_Folder;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import edu.ftiuksw.tr_pam_mamam.Login;
import edu.ftiuksw.tr_pam_mamam.R;

public class ModernRestoFragment extends Fragment {
    private GridLayout gridLayout;
    private CardView meCardView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_modern_resto, container, false);

        gridLayout = view.findViewById(R.id.home_grid);
        setClickEvent(gridLayout);
        return view;
    }

    /**
     * click listener for cardview in home fragment
     */
    private void setClickEvent(final GridLayout gridLayout) {
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            final CardView cardView = (CardView) gridLayout.getChildAt(i);
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getActivity(), Login.class));
                }
            });
        }
    }

}