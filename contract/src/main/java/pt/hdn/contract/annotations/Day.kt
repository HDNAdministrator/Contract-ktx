package pt.hdn.contract.annotations

import androidx.annotation.IntDef
import kotlin.annotation.AnnotationTarget.*

@Retention(AnnotationRetention.SOURCE)
@Target(CLASS, TYPE, VALUE_PARAMETER, PROPERTY)
@IntDef(Day.DAY_1, Day.DAY_2, Day.DAY_3, Day.DAY_4, Day.DAY_5, Day.DAY_6, Day.DAY_7, Day.DAY_8, Day.DAY_9, Day.DAY_10, Day.DAY_11, Day.DAY_12, Day.DAY_13, Day.DAY_14, Day.DAY_15, Day.DAY_16, Day.DAY_17, Day.DAY_18, Day.DAY_19, Day.DAY_20, Day.DAY_21, Day.DAY_22, Day.DAY_23, Day.DAY_24, Day.DAY_25, Day.DAY_26, Day.DAY_27, Day.DAY_28)
annotation class Day {
    companion object {
        const val DAY_1 = 1
        const val DAY_2 = 2
        const val DAY_3 = 3
        const val DAY_4 = 4
        const val DAY_5 = 5
        const val DAY_6 = 6
        const val DAY_7 = 7
        const val DAY_8 = 8
        const val DAY_9 = 9
        const val DAY_10 = 10
        const val DAY_11 = 11
        const val DAY_12 = 12
        const val DAY_13 = 13
        const val DAY_14 = 14
        const val DAY_15 = 15
        const val DAY_16 = 16
        const val DAY_17 = 17
        const val DAY_18 = 18
        const val DAY_19 = 19
        const val DAY_20 = 20
        const val DAY_21 = 21
        const val DAY_22 = 22
        const val DAY_23 = 23
        const val DAY_24 = 24
        const val DAY_25 = 25
        const val DAY_26 = 26
        const val DAY_27 = 27
        const val DAY_28 = 28
    }
}
