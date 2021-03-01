package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue
import pt.hdn.contract.schemas.Schema

@Parcelize
data class Task(
    @Expose var specialityType: IdNameData,
    @Expose var schemas: MutableList<@RawValue Schema> = mutableListOf(),
    @Expose var responsibilities: MutableList<IdNameData>? = null,
    @Expose var exclusivity: Boolean? = null
    ) : Parcelable {

    //region vars
    val isValid: Boolean; get() = with(schemas) { isNotEmpty() && all { isValid } } && responsibilities?.isNotEmpty() ?: true
    //endregion vars


//    companion object : Parceler<Task> {
//        override fun Task.write(parcel: Parcel, flags: Int) {
//
//        }
//
//        override fun create(parcel: Parcel): Task = with(parcel) { Task(readParcelable(IdNameData::class.java.classLoader)!!, readArrayList(Schema::class.java.classLoader) as MutableList<Schema>, readInt().let { if (it == 1) readArrayList(IdNameData::class.java.classLoader) as MutableList<IdNameData> else null }, readInt().let { if (it == 1) readInt() == 1 else null }) }
//    }
}