package com.ashencostha.barcodescanner;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.mlkit.vision.barcode.BarcodeScanner;
import com.google.mlkit.vision.barcode.BarcodeScanning;
import com.google.mlkit.vision.barcode.common.Barcode;
import com.google.mlkit.vision.common.InputImage;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnScan;
    private ImageView barCodeImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnScan = findViewById(R.id.btnScan);
        barCodeImage = findViewById(R.id.imageViewBarCode);
        BitmapDrawable drawable = (BitmapDrawable) barCodeImage.getDrawable();
        Bitmap bitmap = drawable.getBitmap();


        InputImage image = InputImage.fromBitmap(bitmap,0);
        btnScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BarcodeScanner barcodeScanner = BarcodeScanning.getClient();
                barcodeScanner.process(image).addOnSuccessListener(new OnSuccessListener<List<Barcode>>() {
                    @Override
                    public void onSuccess(List<Barcode> barcodes) {
                        for(Barcode barcode : barcodes){
                            String barcodeData = barcode.getRawValue();
                            Toast.makeText(MainActivity.this, barcodeData, Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });

            }
        });



    }
}