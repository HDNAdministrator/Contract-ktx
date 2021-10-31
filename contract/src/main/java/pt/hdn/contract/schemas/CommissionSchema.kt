package pt.hdn.contract.schemas

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Err
import pt.hdn.contract.annotations.Parameter.Companion.CUT
import pt.hdn.contract.annotations.Parameter.Companion.LOWER_BOUND
import pt.hdn.contract.annotations.Parameter.Companion.SOURCE
import pt.hdn.contract.annotations.Parameter.Companion.UPPER_BOUND
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
    @IgnoredOnParcel @Err override val isValid: Int; get() = validate()
    //endregion vars

    companion object : Deserializer<CommissionSchema> {
        override fun deserialize(json: JsonObject): CommissionSchema = with(json) {
            CommissionSchema(
                cut = this[CUT].asBigDecimal,
                source = this[SOURCE].asInt,
                lowerBound = this[LOWER_BOUND]?.asBigDecimal,
                upperBound = this[UPPER_BOUND]?.asBigDecimal
            )
        }
    }

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

    @Err private fun validate(): Int {
        return when {
            cut?.let { it <= ZERO } != false -> Err.CUT
            source == null -> Err.SOURCE
            lowerBound?.let { it < ZERO } != false -> Err.LOWER_BOUND
            upperBound?.let { it > ONE } != false -> Err.UPPER_BOUND
            lowerBound!! >= upperBound -> Err.REVERSED_BOUNDS
            else -> Err.NONE
        }
    }
}
