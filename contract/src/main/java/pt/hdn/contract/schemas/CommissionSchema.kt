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
data class CommissionSchema(
    @Expose val cut: BigDecimal,
    @Expose @SourceType override val source: Int,
    @Expose val lowerBound: BigDecimal?,
    @Expose val upperBound: BigDecimal?
) : Schema {

    //region vars
    @IgnoredOnParcel @SchemaType @Expose override val id: Int = SchemaType.COMMISSION
    @IgnoredOnParcel override val isValid: Boolean; get() = cut > ZERO && lowerBound?.let { it >= ZERO } == true && upperBound?.let { it <= ONE } == true && lowerBound < upperBound
    //endregion vars

    companion object : Parceler<CommissionSchema>, Deserializer<CommissionSchema> {
        override fun CommissionSchema.write(parcel: Parcel, flags: Int) { with(parcel) { writeString(cut.toString()); writeInt(source); writeString(lowerBound?.toString()); writeString(upperBound?.toString()); writeInt(id) } }

        override fun create(parcel: Parcel): CommissionSchema = with(parcel) { CommissionSchema(BigDecimal(readString()), readInt(), readString()?.let { BigDecimal(it) }, readString()?.let { BigDecimal(it) }) }

        override fun deserialize(json: JsonObject): CommissionSchema = with(json) { with(Parameter) { CommissionSchema(get(CUT).asBigDecimal, get(SOURCE).asInt, get(LOWER_BOUND)?.asBigDecimal, get(UPPER_BOUND)?.asBigDecimal) } }
    }
}
