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
import java.math.BigDecimal.ONE

@Parcelize
data class ObjectiveSchema(
    @Expose val bonus: BigDecimal,
    @Expose @SourceType override val source: Int,
    @Expose val lowerBound: BigDecimal?,
    @Expose val upperBound: BigDecimal?
) : Schema, Parcelable {

    //region vars
    @IgnoredOnParcel @SchemaType @Expose override val id: Int = SchemaType.COMMISSION
    @IgnoredOnParcel override val isValid: Boolean; get() = bonus > ZERO && lowerBound?.let { it >= ZERO } == true && upperBound?.let { it <= ONE } == true && lowerBound < upperBound
    //endregion vars

    companion object : Parceler<ObjectiveSchema>, Schema.Companion {
        override fun ObjectiveSchema.write(parcel: Parcel, flags: Int) {
            with(parcel) { writeString(bonus.toString()); writeInt(source); writeString(lowerBound?.toString()); writeString(upperBound?.toString()) }
        }

        override fun create(parcel: Parcel): ObjectiveSchema {
            return ObjectiveSchema(BigDecimal(parcel.readString()), parcel.readInt(), parcel.readString()?.let { BigDecimal(it) }, parcel.readString()?.let { BigDecimal(it) })
        }

        override fun deserialize(json: JsonObject) = with(json) { ObjectiveSchema(this[Parameter.BONUS].asBigDecimal, this[Parameter.SOURCE].asInt, this[Parameter.LOWER_BOUND]?.asBigDecimal, this[Parameter.UPPER_BOUND].asBigDecimal) }
    }
}