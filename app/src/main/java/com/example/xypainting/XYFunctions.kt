package com.example.xypainting

class XYFunctions {
    companion object {
        var canvasHeight: Double ?= null


        var canvasWidth: Double ?= null


        var coefficientWidth: Double = 0.0
        var coefficientHeight: Double = 0.0

        fun setCanvasWidth(width:Double) {
            canvasWidth = width
        }

        fun setCanvasHeight(height:Double) {4
            canvasHeight = height
        }

        fun  calculateCoefficientHeight(y:Double, Y:Double){
            canvasHeight = Y
            coefficientHeight = Y/y
        }

        fun  calculateCoefficientWidth(x:Double, X:Double){
            canvasWidth = X
            coefficientWidth =  X/x
        }

        fun mappingHeight(y: Double): Double {
            return canvasHeight?.let { y * coefficientHeight } ?: 0.0

        }

        fun mappingWidth(x: Double): Double {
            return canvasWidth?.let { x * coefficientWidth } ?: 0.0
        }

    }
}