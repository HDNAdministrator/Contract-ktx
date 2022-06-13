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
data class ObjectiveSchema(
    @Expose var bonus: BigDecimal? = null,
    @Expose @SourceType override var source: Int? = null,
    @Expose var lowerBound: BigDecimal? = null,
    @Expose var upperBound: BigDecimal? = null
) : Schema {

    //region vars
    @IgnoredOnParcel @SchemaType @Expose override val id: Int = SchemaType.OBJECTIVE
    //endregion vars

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ObjectiveSchema) return false

        if (bonus != other.bonus) return false
        if (source != other.source) return false
        if (lowerBound != other.lowerBound) return false
        if (upperBound != other.upperBound) return false

        return true
    }

    override fun hashCode(): Int {
        var result = bonus?.hashCode() ?: 0
        result = 31 * result + (source ?: 0)
        result = 31 * result + (lowerBound?.hashCode() ?: 0)
        result = 31 * result + (upperBound?.hashCode() ?: 0)
        return result
    }

    override fun calculate(value: BigDecimal?): BigDecimal = bonus!!

    override fun clone(): ObjectiveSchema = copy()

    @Err override fun validate(schema: Schema?): Int {
        return when {
            schema?.let { it !is ObjectiveSchema } == true -> Err.DIFF_SCHEMA
            this == schema -> Err.NO_CHANGE
            bonus?.let { it <= ZERO } != false -> Err.BONUS
            source == null -> Err.SOURCE
            lowerBound?.let { it < ZERO } == true -> Err.LOWER_BOUND
            upperBound?.let { it < ZERO } == true -> Err.UPPER_BOUND
            lowerBound?.let { lb -> upperBound?.let { ub -> ub <= lb } } == true -> Err.REVERSED_BOUNDS
            else -> Err.NONE
        }
    }
}