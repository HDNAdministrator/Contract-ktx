package pt.hdn.contract.schemas

import android.os.Parcel
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
data class FixSchema(
    @Expose val fix: BigDecimal
) : Schema {

    //region vars
    @IgnoredOnParcel @Expose @SourceType override val source: Int = SourceType.NONE
    @IgnoredOnParcel @Expose @SchemaType override val id: Int = SchemaType.FIX
    @IgnoredOnParcel override val isValid: Boolean; get() = fix > ZERO
    //endregion vars

    companion object : Parceler<FixSchema>, Deserializer<FixSchema> {
        override fun FixSchema.write(parcel: Parcel, flags: Int) { parcel.writeString(fix.toString()) }

        override fun create(parcel: Parcel): FixSchema = FixSchema(BigDecimal(parcel.readString()))

        override fun deserialize(json: JsonObject): FixSchema = with(json) { FixSchema(this[Parameter.FIX].asBigDecimal) }
    }
}