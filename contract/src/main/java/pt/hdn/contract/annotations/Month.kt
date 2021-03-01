package pt.hdn.contract.annotations

import androidx.annotation.IntDef

@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
@IntDef(Month.JANUARY, Month.FEBRUARY, Month.MARCH, Month.APRIL, Month.MAY, Month.JUNE, Month.JULY, Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER)
annotation class Month {
    companion object {
        const val JANUARY: Int = 0
        const val FEBRUARY: Int = 1
        const val MARCH: Int = 2
        const val APRIL: Int = 3
        const val MAY: Int = 4
        const val JUNE: Int = 5
        const val JULY: Int = 6
        const val AUGUST: Int = 7
        const val SEPTEMBER: Int = 8
        const val OCTOBER: Int = 9
        const val NOVEMBER: Int = 10
        const val DECEMBER: Int = 11
    }
}
