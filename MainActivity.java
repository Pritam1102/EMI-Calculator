package com.example.emicalculator;

import static java.lang.String.*;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import com.example.emicalculator.databinding.ActivityMainBinding;
import java.util.Objects;


public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.pSeekbar.incrementProgressBy(500);
        binding.pSeekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress/500;
                progress = progress*500;
                binding.principal.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//Not required
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
//Not required
            }
        });


        binding.button.setOnClickListener(v -> {
            int P = 0;
            float r =0;
            float n =0;

            if (TextUtils.isEmpty(Objects.requireNonNull(binding.principal.getText()).toString())) {
                binding.principalError.setVisibility(View.VISIBLE);
            }
            if (!TextUtils.isEmpty(binding.principal.getText().toString())){
                P = Integer.parseInt(binding.principal.getText().toString());
                binding.principalError.setVisibility(View.INVISIBLE);
            }
            if (TextUtils.isEmpty(binding.interest.getText().toString())) {
                binding.interestError.setVisibility(View.VISIBLE);
            }
            if (!TextUtils.isEmpty(binding.interest.getText().toString())){
                r = Float.parseFloat(binding.interest.getText().toString());
                binding.interestError.setVisibility(View.INVISIBLE);
            }

            if (TextUtils.isEmpty(binding.duration.getText().toString())) {
                binding.durationError.setVisibility(View.VISIBLE);
            }
            if (!TextUtils.isEmpty(binding.duration.getText().toString())){
                n = Float.parseFloat(binding.duration.getText().toString());
                binding.durationError.setVisibility(View.INVISIBLE);
            }

            float i = r/1200;

            Double E  = (P*i*Math.pow(1 +i, 12*n))/(Math.pow(1+i, 12*n)-1);
            String x = String.format("%.2f", E);
            binding.emi.setText(x);

            binding.text.setText("Your monthly EMI would be " + x);

        });
    }
}