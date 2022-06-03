package pt.hdn.contract.util

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize
import java.util.*

@Parcelize
data class UUIDNameData(
    @Expose val uuid: UUID,
    @Expose val name: String
) : Parcelable {
    override fun toString() = name
}
