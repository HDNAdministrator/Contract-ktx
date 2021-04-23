package pt.hdn.contract.annotations

import androidx.annotation.StringDef
import kotlin.annotation.AnnotationTarget.*

@Retention(AnnotationRetention.SOURCE)
@Target(CLASS, TYPE, VALUE_PARAMETER, FIELD)
@StringDef(Parameter.FIX, Parameter.RATE, Parameter.CUT, Parameter.BONUS, Parameter.SOURCE, Parameter.LOWER_BOUND, Parameter.UPPER_BOUND, Parameter.THRESHOLD, Parameter.IS_ABOVE)
annotation class Parameter {
    companion object {
       const val ID: String = "id"
       const val FIX: String =  "fix"
       const val RATE: String =  "rate"
       const val CUT: String =  "cut"
       const val BONUS: String =  "bonus"
       const val SOURCE: String =  "source"
       const val LOWER_BOUND: String =  "lowerBound"
       const val UPPER_BOUND: String =  "upperBound"
       const val THRESHOLD: String =  "threshold"
       const val IS_ABOVE: String =  "isAbove"
    }
}
