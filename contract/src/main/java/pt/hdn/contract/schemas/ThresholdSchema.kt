package pt.hdn.contract.schemas

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Parameter
import pt.hdn.contract.annotations.SchemaType
import pt.hdn.contract.annotations.SourceType
import java.math.BigDecimal
import java.math.BigDecimal.ZERO

@Parcelize
data class ThresholdSchema(
    @Expose val bonus: BigDecimal,
    @Expose @SourceType override val source: Int,
    @Expose val threshold: BigDecimal,
    @Expose val isAbove: Boolean
) : Schema {

    //region vars
    @IgnoredOnParcel @Expose @SchemaType override val id: Int = SchemaType.COMMISSION
    @IgnoredOnParcel override val isValid: Boolean; get() = bonus > ZERO && threshold > ZERO
    //endregion vars

    companion object : Parceler<ThresholdSchema>, Deserializer<ThresholdSchema> {
        override fun ThresholdSchema.write(parcel: Parcel, flags: Int) { with(parcel) { writeString(bonus.toString()); writeInt(source); writeString(threshold.toString()); writeInt(if (isAbove) 1 else 0) } }

        override fun create(parcel: Parcel): ThresholdSchema = ThresholdSchema(BigDecimal(parcel.readString()), parcel.readInt(), BigDecimal(parcel.readString()), parcel.readInt() == 1)

        override fun deserialize(json: JsonObject): ThresholdSchema = with(json) { with(Parameter) { ThresholdSchema(get(BONUS).asBigDecimal, get(SOURCE).asInt, get(THRESHOLD).asBigDecimal, get(IS_ABOVE).asBoolean) } }
    }
}
