package pt.hdn.contract.annotations

import androidx.annotation.StringDef
import kotlin.annotation.AnnotationTarget.*

@Retention(AnnotationRetention.SOURCE)
@Target(CLASS, TYPE, VALUE_PARAMETER, PROPERTY)
@StringDef(SchemaType.FIX, SchemaType.RATE, SchemaType.COMMISSION, SchemaType.OBJECTIVE, SchemaType.THRESHOLD)
annotation class SchemaType {
    companion object {
        const val FIX: String = "58c6c3cc-5228-11ec-960f-ac1f6bab0326"
        const val RATE: String = "6b03bb44-5228-11ec-960f-ac1f6bab0326"
        const val COMMISSION: String = "7871ca2e-5228-11ec-960f-ac1f6bab0326"
        const val OBJECTIVE: String = "899d5455-5228-11ec-960f-ac1f6bab0326"
        const val THRESHOLD: String = "b1b7b27f-5228-11ec-960f-ac1f6bab0326"
    }
}
