package pt.hdn.contract.annotations

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(DaysPeriod.WORK_DAYS, DaysPeriod.WEEKEND, DaysPeriod.EVERY_DAY)
annotation class DaysPeriod {
    companion object {
        const val WORK_DAYS = 0
        const val WEEKEND = 1
        const val EVERY_DAY = 2
    }
}
