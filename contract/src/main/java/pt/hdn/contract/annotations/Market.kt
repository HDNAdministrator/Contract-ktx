package pt.hdn.contract.annotations

import androidx.annotation.IntDef
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@IntDef(Market.NONE, Market.PERSON, Market.SERVICE)
annotation class Market {
    companion object {
        const val NONE = 0
        const val PERSON = 1
        const val SERVICE = 2
    }
}