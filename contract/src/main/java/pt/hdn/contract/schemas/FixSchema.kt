package pt.hdn.contract.schemas

import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Err
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

@Parcelize
data class FixSchema(
    @Expose var fix: BigDecimal? = null
) : Schema {

    //region vars
    @IgnoredOnParcel @Expose @SourceType override var source: Int? = SourceType.NONE
    @IgnoredOnParcel @Expose @SchemaType override val id: Int = SchemaType.FIX
    //endregion vars

    override fun calculate(value: BigDecimal?): BigDecimal = fix!!

    override fun clone(): FixSchema = copy()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is FixSchema) return false

        if (fix != other.fix) return false

        return true
    }

    override fun hashCode(): Int {
        return fix?.hashCode() ?: 0
    }

    @Err override fun validate(schema: Schema?): Int {
        return when {
            schema?.let { it !is FixSchema } == true -> Err.DIFF_SCHEMA
            this == schema -> Err.NO_CHANGE
            fix?.let { it == ZERO } != false -> Err.FIX
            else -> Err.NONE
        }
    }
}