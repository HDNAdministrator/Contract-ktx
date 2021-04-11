package pt.hdn.contract.annotations

import androidx.annotation.IntDef
import kotlin.annotation.AnnotationRetention.SOURCE

@Retention(SOURCE)
@IntDef(MarketType.NONE, MarketType.PERSON, MarketType.SERVICE)
annotation class MarketType {
    companion object {
        const val NONE = 0
        const val PERSON = 1
        const val SERVICE = 2
    }
}