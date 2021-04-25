package pt.hdn.contract.schemas

import android.os.Parcel
import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parceler
import kotlinx.parcelize.Parcelize
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
    @IgnoredOnParcel override val isValid: Boolean; get() = cut?.let { it > ZERO } == true && lowerBound?.let { lb -> lb >= ZERO && upperBound?.let { ub -> ub <= ONE && lb < ub } == true } == true
    //endregion vars

    companion object : Parceler<CommissionSchema>, Deserializer<CommissionSchema> {
        override fun CommissionSchema.write(parcel: Parcel, flags: Int) { with(parcel) { writeString(cut?.toString()); writeInt(if (source == null) 0 else 1); source?.let { writeInt(it) }; writeString(lowerBound?.toString()); writeString(upperBound?.toString()); writeInt(id) } }

        override fun create(parcel: Parcel): CommissionSchema = with(parcel) { CommissionSchema(readString()?.toBigDecimal(), if (readInt() == 1) readInt() else null, readString()?.toBigDecimal(), readString()?.toBigDecimal()) }

        override fun deserialize(json: JsonObject): CommissionSchema = with(json) { CommissionSchema(this[CUT].asBigDecimal, this[SOURCE].asInt, this[LOWER_BOUND]?.asBigDecimal, this[UPPER_BOUND]?.asBigDecimal) }
    }

    override fun calculate(value: BigDecimal?): BigDecimal = (cut!! * value!!).setScale(2, HALF_EVEN)

    override fun clone(): CommissionSchema = copy()
}
