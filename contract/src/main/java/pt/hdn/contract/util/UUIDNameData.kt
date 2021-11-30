package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class UUIDNameData(
    @Expose val uuid: String,
    @Expose val name: String
) : Parcelable {
    override fun toString() = name
}
