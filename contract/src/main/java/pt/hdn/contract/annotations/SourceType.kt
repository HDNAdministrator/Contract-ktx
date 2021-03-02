package pt.hdn.contract.annotations

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@Target(AnnotationTarget.CLASS, AnnotationTarget.TYPE, AnnotationTarget.VALUE_PARAMETER)
@IntDef(SourceType.NONE, SourceType.PERSON_PROFIT, SourceType.TEAM_PROFIT, SourceType.DEPARTMENT_PROFIT, SourceType.COMPANY_PROFIT, SourceType.DISTANCE, SourceType.TIME)
annotation class SourceType {
    companion object {
        const val NONE: Int = -1
        const val PERSON_PROFIT: Int = 0
        const val TEAM_PROFIT: Int = 1
        const val DEPARTMENT_PROFIT: Int = 2
        const val COMPANY_PROFIT: Int = 3
        const val DISTANCE: Int = 4
        const val TIME: Int = 5
    }
}
