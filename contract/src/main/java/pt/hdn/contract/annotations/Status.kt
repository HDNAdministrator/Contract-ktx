package pt.hdn.contract.annotations

import androidx.annotation.IntDef

@Retention(AnnotationRetention.SOURCE)
@IntDef(Status.INVALID, Status.EXPIRED, Status.PENDING, Status.VALID)
annotation class Status {
    companion object {
        const val INVALID: Int = -2
        const val EXPIRED: Int = -1
        const val PENDING: Int = 0
        const val VALID: Int = 1
    }
}
