package pt.hdn.contract.annotations

import androidx.annotation.StringDef
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@StringDef
annotation class Specialities {
    companion object {
        const val DRIVER: String = "ea8b7b1d-5228-11ec-960f-ac1f6bab0326"
        const val CEO: String = "f416e64c-5228-11ec-960f-ac1f6bab0326"
    }
}
