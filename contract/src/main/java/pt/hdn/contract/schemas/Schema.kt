package pt.hdn.contract.schemas

import android.os.Parcelable
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType
import java.math.BigDecimal

interface Schema: Parcelable, Cloneable {
    @SourceType var source: Int?
    @SchemaType val type: Int
    val isValid: Boolean

    fun calculate(value: BigDecimal? = null): BigDecimal

    public override fun clone(): Schema
}