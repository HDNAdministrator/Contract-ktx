package pt.hdn.contract.annotations

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(MonthType.MONTHS, MonthType.PERIOD)
annotation class MonthType {
    companion object {
        const val MONTHS: Int = 0
        const val PERIOD: Int = 1
    }
}
