package pt.hdn.contract.annotations

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(DaysType.DAYS, DaysType.DOW, DaysType.PERIOD)
annotation class DaysType {
    companion object {
        const val DAYS = 0
        const val DOW = 1
        const val PERIOD = 2
    }
}
