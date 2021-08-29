package pt.hdn.contract.annotations

import androidx.annotation.IntDef
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@IntDef()
annotation class Specialities {
    companion object {
        const val DRIVER: Int = 3
        const val CEO: Int = 19
    }
}
