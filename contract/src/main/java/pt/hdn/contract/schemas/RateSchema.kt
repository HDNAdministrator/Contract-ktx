package pt.hdn.contract.schemas

import android.os.Parcel
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
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
    @IgnoredOnParcel override val isValid: Boolean; get() = rate?.let { it > ZERO } == true && source != null
    //endregion vars

    companion object : Parceler<RateSchema>, Deserializer<RateSchema> {
        override fun RateSchema.write(parcel: Parcel, flags: Int) { with(parcel) { writeString(rate?.toString()); writeInt(if (source == null) 0 else 1); source?.let { writeInt(it) } } }

        override fun create(parcel: Parcel): RateSchema = with(parcel) { RateSchema(readString()?.toBigDecimal(), if (readInt() == 1) readInt() else null) }

        override fun deserialize(json: JsonObject): RateSchema = with(json) { RateSchema(this[RATE].asBigDecimal, this[SOURCE].asInt) }
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
}
