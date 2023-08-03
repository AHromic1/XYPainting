package com.example.xypainting

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private lateinit var bttnCalculate: Button
    private lateinit var textPaintingWidth: EditText
    private lateinit var textPaintingHeight: EditText
    private lateinit var textPhotoWidth: EditText
    private lateinit var textPhotoHeight: EditText
    private lateinit var textResultHeight: TextView
    private lateinit var textResultWidth: TextView
    private lateinit var textHeight: EditText
    private lateinit var textWidth: EditText
    private lateinit var bttnUpload: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bttnCalculate = findViewById(R.id.buttonCalculate)
        textPaintingWidth = findViewById(R.id.editTextPaintingWidth)
        textPaintingHeight = findViewById(R.id.editTextPaintingHeight)
        textPhotoWidth = findViewById(R.id.editTextPhotoWidth)
        textPhotoHeight = findViewById(R.id.editTextPhotoHeight)
        textPhotoWidth = findViewById(R.id.editTextPhotoWidth)
        textResultHeight = findViewById(R.id.editTextHeightResult)
        textResultWidth = findViewById(R.id.editTextWidthResult)
        textHeight = findViewById(R.id.editTextHeight)
        textWidth = findViewById(R.id.editTextWidth)

        bttnCalculate.setOnClickListener {
            val paintingHeightText = textPaintingHeight.text.toString()
            val paintingWidthText = textPaintingWidth.text.toString()
            val photoHeightText = textPhotoHeight.text.toString()
            val photoWidthText = textPhotoWidth.text.toString()

            val inputHeightText = textHeight.text.toString()
            val inputWidthText = textWidth.text.toString()

            if (paintingHeightText.isNotEmpty() &&
                paintingWidthText.isNotEmpty() &&
                photoHeightText.isNotEmpty() &&
                photoWidthText.isNotEmpty() &&
                inputHeightText.isNotEmpty() &&
                inputWidthText.isNotEmpty()
            ) {
                var paintingHeight = textPaintingHeight.text.toString().toDouble()
                var paintingWidth = textPaintingWidth.text.toString().toDouble()
                var photoHeight = textPhotoHeight.text.toString().toDouble()
                var photoWidth = textPhotoWidth.text.toString().toDouble()

                if (paintingHeight == null) paintingHeight = 0.0
                if (paintingWidth == null) paintingWidth = 0.0
                if (photoHeight == null) photoHeight = 0.0
                if (photoWidth == null) photoWidth = 0.0

                XYFunctions.setCanvasHeight(paintingHeight)
                XYFunctions.setCanvasWidth(paintingWidth)

                XYFunctions.calculateCoefficientHeight(photoHeight, paintingHeight)
                XYFunctions.calculateCoefficientWidth(photoWidth, paintingWidth)
                //just a simple check
                println("k height" + XYFunctions.coefficientHeight)
                println("k width" + XYFunctions.coefficientWidth)

                var inputHeight = textHeight.text.toString().toDouble()
                var inputWidth = textWidth.text.toString().toDouble()

                if (inputHeight == null) inputHeight = 0.0
                if (inputWidth == null) inputWidth = 0.0

                var resultHeight = XYFunctions.mappingHeight(inputHeight)
                var resultWidth = XYFunctions.mappingWidth(inputWidth)

                //textResultHeight.setText(resultHeight.toString())
                // textResultWidth.setText(resultWidth.toString())

                //displaying results formatted to 2 decimal places
                val decimalFormat = DecimalFormat("#.00")
                val formattedResultHeight = decimalFormat.format(resultHeight)
                val formattedResultWidth = decimalFormat.format(resultWidth)

                textResultHeight.text = formattedResultHeight
                textResultWidth.text = formattedResultWidth
            }
        }

        bttnUpload = findViewById(R.id.buttonUpload)

        bttnUpload.setOnClickListener {
            val paintingHeightText = textPaintingHeight.text.toString()
            val paintingWidthText = textPaintingWidth.text.toString()

            if (paintingHeightText.isNotEmpty() &&
                paintingWidthText.isNotEmpty()
            ) {
                var paintingHeight = textPaintingHeight.text.toString().toDouble()
                var paintingWidth = textPaintingWidth.text.toString().toDouble()
                XYFunctions.setCanvasHeight(paintingHeight)
                XYFunctions.setCanvasWidth(paintingWidth)
            }
            else{
                XYFunctions.setCanvasHeight(0.0)
                XYFunctions.setCanvasWidth(0.0)
            }

                //println("HEIGHT  WIDTH" + XYFunctions.canvasHeight + XYFunctions.canvasWidth)

            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
        }

    }
}