package com.example.xypainting

import android.os.Bundle
import android.view.MotionEvent
import android.widget.ImageView
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.xypainting.databinding.ActivityUploadBinding

class UploadActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)


        imageView = findViewById(R.id.painting2)

        imageView.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {  //actions are performed when user lifts the finger og the image
                val imageWidth = imageView.width.toFloat()
                val imageHeight = imageView.height.toFloat()
                println("dimensions: $imageWidth $imageHeight")
                val clickedX = motionEvent.x // Clicked X-coordinate in pixels
                val clickedY = motionEvent.y // Clicked Y-coordinate in pixels

                // Calculate the actual coordinates in centimeters
                val actualImageWidthInCm = 21 // Replace with actual image width in centimeters
                val actualImageHeightInCm = 29.7 // Replace with actual image height in centimeters

                val centimetersX = (clickedX / imageWidth) * actualImageWidthInCm
                val centimetersY = actualImageHeightInCm - (clickedY / imageHeight) * actualImageHeightInCm

                println("Pixels: $clickedX $clickedY")
                println("Centimeters: $centimetersX $centimetersY")
            }
            true // Return 'true' to consume the touch event - no further actions are needed
        }



}

}