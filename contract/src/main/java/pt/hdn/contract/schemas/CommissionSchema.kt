package pt.hdn.contract.schemas

import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Err
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType
import java.math.BigDecimal
import java.math.BigDecimal.ONE
import java.math.BigDecimal.ZERO
import java.math.RoundingMode.HALF_EVEN

@Parcelize
data class CommissionSchema(
    @Expose var cut: BigDecimal? = null,
    @Expose @SourceType override var source: Int? = null,
    @Expose var lowerBound: BigDecimal? = null,
    @Expose var upperBound: BigDecimal? = null
) : Schema {

    //region vars
    @IgnoredOnParcel @SchemaType @Expose override val id: Int = SchemaType.COMMISSION
    //endregion vars

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CommissionSchema) return false

        if (cut != other.cut) return false
        if (source != other.source) return false
        if (lowerBound != other.lowerBound) return false
        if (upperBound != other.upperBound) return false

        return true
    }

    override fun hashCode(): Int {
        var result = cut?.hashCode() ?: 0
        result = 31 * result + (source ?: 0)
        result = 31 * result + (lowerBound?.hashCode() ?: 0)
        result = 31 * result + (upperBound?.hashCode() ?: 0)
        return result
    }

    override fun calculate(value: BigDecimal?): BigDecimal = (cut!! * value!!).setScale(2, HALF_EVEN)

    override fun clone(): CommissionSchema = copy()

    @Err override fun validate(schema: Schema?): Int {
        return when {
            schema?.let { it !is CommissionSchema } == true -> Err.DIFF_SCHEMA
            this == schema -> Err.NO_CHANGE
            cut?.let { it <= ZERO || it >= ONE } != false -> Err.CUT
            source == null -> Err.SOURCE
            lowerBound?.let { it < ZERO } == true -> Err.LOWER_BOUND
            upperBound?.let { it < ZERO } == true -> Err.UPPER_BOUND
            lowerBound?.let { lb -> upperBound?.let { ub -> ub <= lb } } == true -> Err.REVERSED_BOUNDS
            else -> Err.NONE
        }
    }
}
