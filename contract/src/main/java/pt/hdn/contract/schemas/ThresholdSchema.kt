package pt.hdn.contract.schemas

import android.os.Parcel
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Parameter.Companion.BONUS
import pt.hdn.contract.annotations.Parameter.Companion.IS_ABOVE
import pt.hdn.contract.annotations.Parameter.Companion.SOURCE
import pt.hdn.contract.annotations.Parameter.Companion.THRESHOLD
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import java.math.RoundingMode

@Parcelize
data class ThresholdSchema(
    @Expose var bonus: BigDecimal? = null,
    @Expose @SourceType override var source: Int? = null,
    @Expose var threshold: BigDecimal? = null,
    @Expose var isAbove: Boolean? = null
) : Schema {

    //region vars
    @IgnoredOnParcel @Expose @SchemaType override val id: Int = SchemaType.COMMISSION
    @IgnoredOnParcel override val isValid: Boolean; get() = bonus?.let { it > ZERO } == true && threshold?.let { it > ZERO } == true && isAbove != null
    //endregion vars

    companion object : Parceler<ThresholdSchema>, Deserializer<ThresholdSchema> {
        override fun ThresholdSchema.write(parcel: Parcel, flags: Int) { with(parcel) { writeString(bonus?.toString()); writeInt(if (source == null) 0 else 1); source?.let { writeInt(it) }; writeString(threshold?.toString()); writeInt(if (isAbove == null) 0 else 1); isAbove?.let { writeInt(if (it) 1 else 0) } } }

        override fun create(parcel: Parcel): ThresholdSchema = with(parcel) { ThresholdSchema(readString()?.toBigDecimal(), if (readInt() == 1) readInt() else null, readString()?.toBigDecimal(), if (readInt() == 1) readInt() == 1 else null) }

        override fun deserialize(json: JsonObject): ThresholdSchema = with(json) { ThresholdSchema(get(BONUS).asBigDecimal, get(SOURCE).asInt, get(THRESHOLD).asBigDecimal, get(IS_ABOVE).asBoolean) }
    }

    override fun calculate(value: BigDecimal?): BigDecimal = bonus!!

    override fun clone(): ThresholdSchema = copy()
}
