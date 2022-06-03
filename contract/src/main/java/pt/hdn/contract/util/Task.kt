package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.IgnoredOnParcel
import kotlinx.parcelize.Parcelize
import pt.hdn.contract.annotations.Err
import pt.hdn.contract.schemas.Schema

@Parcelize
data class Task(
    @Expose var specialityType: UUIDNameMarketData,
    @Expose var schemas: MutableList<Schema> = mutableListOf(),
    @Expose var responsibilities: List<UUIDNameData>? = null,
    @Expose var exclusivity: Boolean? = null
) : Parcelable, Cloneable {

    public override fun clone(): Task {
        return copy(
            schemas = schemas.mapTo(mutableListOf()) { it.clone() },
            responsibilities = responsibilities?.mapTo(mutableListOf()) { it }
        )
    }

    @Err fun validate(task: Task? = null): Int {
        var err = Err.NONE

        return when {
            this == task -> Err.NO_CHANGE
            schemas.isEmpty() -> Err.SCHEMAS
            schemas.any { it.validate().also { err = it } != Err.NONE } -> err
            responsibilities?.isEmpty()  == true -> Err.RESPONSIBILITIES
            else -> Err.NONE
        }
    }
}