package pt.hdn.contract.annotations

import androidx.annotation.StringDef

@Retention(AnnotationRetention.SOURCE)
@StringDef(Algorithm.SHA512withRSA)
annotation class Algorithm {
    companion object {
        const val SHA512withRSA: String = "SHA512withRSA"
    }
}
