package pt.hdn.contract.annotations

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(MonthsPeriod.MONTHS_ALL, MonthsPeriod.MONTHS_2, MonthsPeriod.MONTHS_3, MonthsPeriod.MONTHS_4, MonthsPeriod.MONTHS_6)
annotation class MonthsPeriod {
    companion object {
        const val MONTHS_ALL: Int =  1
        const val MONTHS_2: Int =  2
        const val MONTHS_3: Int =  3
        const val MONTHS_4: Int =  4
        const val MONTHS_6: Int =  6
    }
}
