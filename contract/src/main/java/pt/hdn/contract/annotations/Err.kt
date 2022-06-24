package pt.hdn.contract.annotations

import androidx.annotation.IntDef
import kotlin.annotation.AnnotationTarget.*

@Retention(AnnotationRetention.SOURCE)
@Target(FUNCTION, PROPERTY, PROPERTY, LOCAL_VARIABLE)
@IntDef()
annotation class Err {
    companion object {
        const val NONE = 0
        const val DAYS = 1
        const val DOW = 2
        const val DAYS_PERIOD = 3
        const val MONTHS = 4
        const val MONTHS_PERIOD = 5
        const val FINISH = 6
        const val FIX = 7
        const val RATE = 8
        const val SOURCE = 9
        const val CUT = 10
        const val LOWER_BOUND = 11
        const val UPPER_BOUND = 12
        const val REVERSED_BOUNDS = 13
        const val BONUS = 14
        const val THRESHOLD = 15
        const val IS_ABOVE = 16
        const val SCHEMAS = 17
        const val RESPONSIBILITIES = 18
        const val TASKS = 19
        const val NO_CHANGE = 20
        const val DIFF_SCHEMA = 21
        const val MORE_THAN_ONE = 22
        const val OVERLAP = 23
    }
}
