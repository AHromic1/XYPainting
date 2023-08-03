package com.example.xypainting

class XYFunctions {
    companion object {

        var coefficientWidth: Double = 0.0

        var coefficientHeight: Double = 0.0

        fun  calculateCoefficientHeight(y:Double, Y:Double){
            coefficientHeight = Y/y
        }

        fun  calculateCoefficientWidth(x:Double, X:Double){
            coefficientWidth =  X/x
        }

        fun mappingHeight(y: Double): Double {
            return y * coefficientHeight

        }

        fun mappingWidth(x: Double): Double {
            return x * coefficientWidth
        }

    }
}