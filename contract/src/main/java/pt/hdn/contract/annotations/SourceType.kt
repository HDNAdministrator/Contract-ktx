package pt.hdn.contract.annotations

import androidx.annotation.IntDef
import kotlin.annotation.AnnotationTarget.*

@Retention(AnnotationRetention.SOURCE)
@Target(CLASS, TYPE, VALUE_PARAMETER, PROPERTY)
@IntDef(SourceType.NONE, SourceType.INDIVIDUAL_REVENUE, SourceType.TEAM_REVENUE, SourceType.DEPARTMENT_REVENUE, SourceType.COMPANY_REVENUE, SourceType.DISTANCE, SourceType.TIME)
annotation class SourceType {
    companion object {
        const val NONE: Int = -1
        const val INDIVIDUAL_REVENUE: Int = 0
        const val TEAM_REVENUE: Int = 1
        const val DEPARTMENT_REVENUE: Int = 2
        const val COMPANY_REVENUE: Int = 3
        const val DISTANCE: Int = 4
        const val TIME: Int = 5
    }
}
