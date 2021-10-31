package pt.hdn.contract.schemas

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Err
import pt.hdn.contract.annotations.Parameter.Companion.BONUS
import pt.hdn.contract.annotations.Parameter.Companion.LOWER_BOUND
import pt.hdn.contract.annotations.Parameter.Companion.SOURCE
import pt.hdn.contract.annotations.Parameter.Companion.UPPER_BOUND
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType
import java.math.BigDecimal
import java.math.BigDecimal.ONE
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
    @IgnoredOnParcel @Err override val isValid: Int; get() = validate()
    //endregion vars

    companion object : Deserializer<ObjectiveSchema> {
        override fun deserialize(json: JsonObject): ObjectiveSchema = with(json) {
            ObjectiveSchema(
                bonus = this[BONUS].asBigDecimal,
                source = this[SOURCE].asInt,
                lowerBound = this[LOWER_BOUND]?.asBigDecimal,
                upperBound = this[UPPER_BOUND].asBigDecimal
            )
        }
    }

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

    @Err private fun validate(): Int {
        return when {
            bonus?.let { it <= ZERO } != false -> Err.BONUS
            source == null -> Err.SOURCE
            lowerBound?.let { it < ZERO } == true -> Err.LOWER_BOUND
            upperBound?.let { it < ZERO } == true -> Err.UPPER_BOUND
            lowerBound?.let { lb -> upperBound?.let { ub -> ub <= lb } } == true -> Err.REVERSED_BOUNDS
            else -> Err.NONE
        }
    }
}