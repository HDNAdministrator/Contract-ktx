package pt.hdn.contract.schemas

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Err
import pt.hdn.contract.annotations.Parameter.Companion.RATE
import pt.hdn.contract.annotations.Parameter.Companion.SOURCE
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.math.RoundingMode.HALF_EVEN

@Parcelize
data class RateSchema(
    @Expose var rate: BigDecimal? = null,
    @Expose @SourceType override var source: Int? = null
) : Schema {

    //region vars
    @IgnoredOnParcel @Expose @SchemaType override val id: Int = SchemaType.RATE
    @IgnoredOnParcel @Err override val isValid: Int; get() = validate()
    //endregion vars

    companion object : Deserializer<RateSchema> {
        override fun deserialize(json: JsonObject): RateSchema = with(json) {
            RateSchema(
                rate = this[RATE].asBigDecimal,
                source = this[SOURCE].asInt
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RateSchema) return false

        if (rate != other.rate) return false
        if (source != other.source) return false

        return true
    }

    override fun hashCode(): Int {
        var result = rate?.hashCode() ?: 0
        result = 31 * result + (source ?: 0)
        return result
    }

    override fun calculate(value: BigDecimal?): BigDecimal = (rate!! * value!!).setScale(2, HALF_EVEN)

    override fun clone(): RateSchema = copy()

    @Err private fun validate(): Int {
        return when {
            rate?.let { it == ZERO } != false -> Err.RATE
            source == null -> Err.SOURCE
            else -> Err.NONE
        }
    }
}
