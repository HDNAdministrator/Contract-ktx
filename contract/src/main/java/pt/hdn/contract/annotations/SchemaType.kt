package pt.hdn.contract.annotations

import androidx.annotation.IntDef
import kotlin.annotation.AnnotationTarget.*

@Retention(AnnotationRetention.SOURCE)
@Target(CLASS, TYPE, VALUE_PARAMETER, PROPERTY)
@IntDef(SchemaType.FIX, SchemaType.RATE, SchemaType.COMMISSION, SchemaType.OBJECTIVE, SchemaType.THRESHOLD)
annotation class SchemaType {
    companion object {
        const val FIX: Int = 0
        const val RATE: Int = 1
        const val COMMISSION: Int = 2
        const val OBJECTIVE: Int = 3
        const val THRESHOLD: Int = 4
    }
}
