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
    @IgnoredOnParcel @Expose @SchemaType override val id: Int = SchemaType.THRESHOLD
    @IgnoredOnParcel override val isValid: Boolean; get() = bonus?.let { it > ZERO } == true && threshold?.let { it > ZERO } == true && isAbove != null
    //endregion vars

    companion object : /*Parceler<ThresholdSchema>, */Deserializer<ThresholdSchema> {
//        override fun ThresholdSchema.write(parcel: Parcel, flags: Int) {
//            with(parcel) {
//                writeString(bonus?.toString())
//                writeInt(if (source == null) 0 else 1)
//                source?.let { writeInt(it) }
//                writeString(threshold?.toString())
//                writeInt(if (isAbove == null) 0 else 1)
//                isAbove?.let { writeInt(if (it) 1 else 0)
//                }
//            }
//        }
//
//        override fun create(parcel: Parcel): ThresholdSchema = with(parcel) {
//            ThresholdSchema(
//                bonus = readString()?.toBigDecimal(),
//                source = if (readInt() == 1) readInt() else null,
//                threshold = readString()?.toBigDecimal(),
//                isAbove = if (readInt() == 1) readInt() == 1 else null
//            )
//        }

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
}
