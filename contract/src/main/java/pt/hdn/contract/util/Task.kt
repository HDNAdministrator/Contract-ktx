package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import pt.hdn.contract.schemas.Schema

@Parcelize
data class Task(
    @Expose var specialityType: IdNameMarketData,
    @Expose var schemas: MutableList<@RawValue Schema> = mutableListOf(),
    @Expose var responsibilities: List<IdNameData>? = null,
    @Expose var exclusivity: Boolean? = null
    ) : Parcelable {

    //region vars
    val isValid: Boolean; get() = with(schemas) { isNotEmpty() && all { isValid } } && responsibilities?.isNotEmpty() ?: true
    //endregion vars
}