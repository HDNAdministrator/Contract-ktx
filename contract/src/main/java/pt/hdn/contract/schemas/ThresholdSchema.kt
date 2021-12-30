package pt.hdn.contract.schemas

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Err
import pt.hdn.contract.annotations.Parameter.Companion.BONUS
import pt.hdn.contract.annotations.Parameter.Companion.IS_ABOVE
import pt.hdn.contract.annotations.Parameter.Companion.SOURCE
import pt.hdn.contract.annotations.Parameter.Companion.THRESHOLD
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

@Parcelize
data class ThresholdSchema(
    @Expose var bonus: BigDecimal? = null,
    @Expose @SourceType override var source: Int? = null,
    @Expose var threshold: BigDecimal? = null,
    @Expose var isAbove: Boolean? = null
) : Schema {

    //region vars
    @IgnoredOnParcel @Expose @SchemaType override val id: Int = SchemaType.THRESHOLD
    //endregion vars

    companion object : Deserializer<ThresholdSchema> {
        override fun deserialize(json: JsonObject): ThresholdSchema = with(json) {
            ThresholdSchema(
                bonus = this[BONUS].asBigDecimal,
                source = this[SOURCE].asInt,
                threshold = this[THRESHOLD].asBigDecimal,
                isAbove = this[IS_ABOVE].asBoolean
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is ThresholdSchema) return false

        if (bonus != other.bonus) return false
        if (source != other.source) return false
        if (threshold != other.threshold) return false
        if (isAbove != other.isAbove) return false

        return true
    }

    override fun hashCode(): Int {
        var result = bonus?.hashCode() ?: 0
        result = 31 * result + (source ?: 0)
        result = 31 * result + (threshold?.hashCode() ?: 0)
        result = 31 * result + (isAbove?.hashCode() ?: 0)
        return result
    }

    override fun calculate(value: BigDecimal?): BigDecimal = bonus!!

    override fun clone(): ThresholdSchema = copy()

    @Err override fun validate(schema: Schema?): Int {
        return when {
            schema?.let { it !is ThresholdSchema } == true -> Err.DIFF_SCHEMA
            schema?.let { this == it } == true -> Err.NO_CHANGE
            bonus?.let { it <= ZERO } != false -> Err.BONUS
            source == null -> Err.SOURCE
            threshold?.let { it <= ZERO } != false -> Err.THRESHOLD
            isAbove == null -> Err.IS_ABOVE
            else -> Err.NONE
        }
    }
}
