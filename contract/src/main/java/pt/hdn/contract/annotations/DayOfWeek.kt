package pt.hdn.contract.annotations

import androidx.annotation.IntDef

@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.SOURCE)
@IntDef(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY, DayOfWeek.SATURDAY, DayOfWeek.SUNDAY)
annotation class DayOfWeek {
    companion object {
        const val MONDAY = 1
        const val TUESDAY = 2
        const val WEDNESDAY = 3
        const val THURSDAY = 4
        const val FRIDAY = 5
        const val SATURDAY = 6
        const val SUNDAY = 7
    }
}
