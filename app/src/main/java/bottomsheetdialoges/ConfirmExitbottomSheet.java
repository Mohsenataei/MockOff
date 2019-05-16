package bottomsheetdialoges;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.deathstroke.uniqueoff1.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import Service.SaveSharedPreference;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ConfirmExitbottomSheet extends BottomSheetDialogFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.confirm_exit_layout,container,false);
        Button Nobutton = v.findViewById(R.id.bottom_sheet_no_button);

        Nobutton.setOnClickListener(view->{
            dismiss();
        });

        Button YesButton = v.findViewById(R.id.bottom_sheet_yes_button);
        YesButton.setOnClickListener(view->{
            SaveSharedPreference.removeAPITOKEN(getActivity());
            dismiss();
        });
        return v;
    }
}
