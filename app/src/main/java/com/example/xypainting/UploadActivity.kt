package com.example.xypainting

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.xypainting.databinding.ActivityUploadBinding
import com.bumptech.glide.Glide
import java.text.DecimalFormat


class UploadActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_IMAGE_PICK = 100
    }
    private lateinit var imagePickLauncher: ActivityResultLauncher<Intent>

    private lateinit var imageView: ImageView
    private lateinit var bttnUpload: Button
    private lateinit var resultHeight: TextView
    private lateinit var resultWidth: TextView
    private lateinit var bttnBack: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        imageView = findViewById(R.id.painting2)

        imageView.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP) {  //actions are performed when user lifts the finger og the image
                val imageWidth = imageView.width.toFloat() //this too is in pixels
                val imageHeight = imageView.height.toFloat()
                //println("dimensions: $imageWidth $imageHeight")
                val clickedX = motionEvent.x // Clicked X-coordinate in pixels
                val clickedY = motionEvent.y // Clicked Y-coordinate in pixels

                // Calculating the actual coordinates in centimeters
                val actualImageWidthInCm = XYFunctions.canvasWidth!!
                val actualImageHeightInCm = XYFunctions.canvasHeight!!

                val centimetersX = (clickedX / imageWidth) * actualImageWidthInCm!!
                val centimetersY = actualImageHeightInCm?.minus((clickedY / imageHeight) * actualImageHeightInCm)

                val decimalFormat = DecimalFormat("#.00")
                resultHeight = findViewById(R.id.editTextHeightResult)
                resultHeight.text = decimalFormat.format(centimetersY)
                resultWidth = findViewById(R.id.editTextWidthResult)
                resultWidth.text = decimalFormat.format(centimetersX) //it works with toString at the end, but not neccessary, automatic conversion happens due to .text bit

                //println("Pixels: $clickedX $clickedY")
                //println("Centimeters: $centimetersX $centimetersY")
            }
            true // Return 'true' to consume the touch event - no further actions are needed
        }

        bttnUpload = findViewById(R.id.button_first)

        // Creating the launcher
        imagePickLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val selectedImageUri = result.data?.data
                // Handling the selected image URI
                Glide.with(this)
                    .load(selectedImageUri)
                    .into(imageView)

            }
        }

        bttnUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            imagePickLauncher.launch(intent)
        }

        bttnBack = findViewById(R.id.buttonPrevious)
        bttnBack.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


}

}