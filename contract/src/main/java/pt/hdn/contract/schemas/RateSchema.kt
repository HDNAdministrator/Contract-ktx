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
data class RateSchema(
    @Expose val rate: BigDecimal,
    @Expose @SourceType override val source: Int
) : Schema, Parcelable {

    //region vars
    @IgnoredOnParcel @Expose @SchemaType override val id: Int = SchemaType.RATE
    @IgnoredOnParcel override val isValid: Boolean; get() = rate > ZERO
    //endregion vars

    companion object : Parceler<RateSchema>, Schema.Companion {
        override fun RateSchema.write(parcel: Parcel, flags: Int) { with(parcel) { writeString(rate.toString()); writeInt(source) } }

        override fun create(parcel: Parcel): RateSchema = RateSchema(BigDecimal(parcel.readString()), parcel.readInt())

        override fun deserialize(json: JsonObject) = with(json) { RateSchema(this[Parameter.RATE].asBigDecimal, this[Parameter.SOURCE].asInt) }
    }
}
